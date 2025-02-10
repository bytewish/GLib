# introduce

This is an Android tree hierarchy selection widget. The widget's code is derived from [github Link](https://github.com/gapowork/android-tree-view?tab=readme-ov-file). Based on this library, additional partial selection functionality  and remove the unable select state.

# how to use

Data Conversion  

```
data class ProductCategoryModel(
    override val nodeViewId: String,
    val name: String,
    val child: List<ProductCategoryModel>,
) : NodeData<ProductCategoryModel> {
    override fun getNodeChild(): List<ProductCategoryModel> {
        return child
    }

    companion object {
        private fun convertToCategoryModelList(productSubList: MutableList<ProductSub>?): MutableList<ProductCategoryModel> {
            if (productSubList == null || productSubList.isEmpty()) return mutableListOf()

            return productSubList.map { productSub ->
                ProductCategoryModel(
                    nodeViewId = productSub.id.toString(),
                    name = productSub.name ?: "Unnamed",
                    child = convertToCategoryModelList(productSub.sub)
                )
            }.toMutableList()
        }

        fun getList(dataList: MutableList<ProductSub>) = convertToCategoryModelList(dataList)
    }

    override fun areItemsTheSame(item: NodeData<ProductCategoryModel>): Boolean {
        return if (item !is ProductCategoryModel) false
        else nodeViewId == item.nodeViewId
    }

    override fun areContentsTheSame(item: NodeData<ProductCategoryModel>): Boolean {
        return if (item !is ProductCategoryModel) false
        else item.name == name && item.child.size == child.size
    }

    override fun getChangePayload(item: NodeData<ProductCategoryModel>): Bundle {
        return Bundle()
    }
}
```

Custom your item layout：We use [indeterminateCheckbox](com.github.sevar83:indeterminate-checkbox:1.0.5@aar) to support three state checkbox, all select/ no select/part select. if you want have tree state, just use it with maven dependency

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.appcompat.widget.LinearLayoutCompat xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:gravity="center_vertical"
    android:minHeight="48dp"
    android:orientation="horizontal"
    android:paddingEnd="12dp"
    tools:ignore="RtlSymmetry">

    <androidx.appcompat.widget.AppCompatImageView
        android:id="@+id/iv_arrow"
        android:layout_width="24dp"
        android:layout_height="24dp"
        android:tint="@color/black_33"
        app:srcCompat="@drawable/data_svg_right_arrow" />

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/tv_department_name"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:paddingStart="8dp"
        android:textColor="@android:color/black"
        tools:text="GAPO" />

    <com.buildware.widget.indeterm.IndeterminateCheckBox
        android:id="@+id/rb_check"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:minWidth="0dp" />

</androidx.appcompat.widget.LinearLayoutCompat>
```

Initialize Selector

```
class MutiSelectCategoryActivity : BaseAppViewBindingActivity<SetCategoryModel, ActivityMultiSelectCategoryBinding>(),
    GapoTreeView.Listener<ProductCategoryModel>{

    private lateinit var treeView: GapoTreeView<ProductCategoryModel>
    private var isShowLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLoading()
        mViewModel.getCategoryAl()
        initListener()
    }

    private fun initListener(){
        mBinding.tvSave.setOnClickListener {
            var selectIds =  treeView.getSelectedNodes()
            var resultList = mutableListOf<String>()
            var resultIntent = Intent()
            if(selectIds.size > 0){
                selectIds.forEach {
                    if(it.child.size < 1){
                        resultList.add(it.nodeViewId)
                    }
                }
                resultIntent.putStringArrayListExtra(CommonCode.pageCategoryName, ArrayList(resultList))
                setResult(CommonCode.rqResultOK,resultIntent)
            }
            finish()
        }
    }

    private fun startBindData(dataList:MutableList<ProductSub>){
        treeView = GapoTreeView.Builder.plant<ProductCategoryModel>(this)
            .withRecyclerView(mBinding.treeView)
            .withLayoutRes(R.layout.view_multi_node_view)
            .setListener(this)
            .setData(ProductCategoryModel.getList(dataList).toMutableList())
            .build()

        (intent.getSerializableExtra(CommonCode.pageParam) as? ArrayList<String>)?.forEach {
            treeView.selectNode(it,true)
        }
    }

    override fun onBind(holder: View, position: Int, item: NodeViewData<ProductCategoryModel>, bundle: Bundle?) {
        val ivArrow = holder.findViewById<AppCompatImageView>(R.id.iv_arrow)
        val cbCheck = holder.findViewById<IndeterminateCheckBox>(R.id.rb_check)
        val tvNode = holder.findViewById<AppCompatTextView>(R.id.tv_department_name)
        val data = item.getData()

        tvNode.text = data.name

        if (item.isLeaf) {
            ivArrow.visibility = View.INVISIBLE
        } else {
            ivArrow.visibility = View.VISIBLE
        }
        val rotateDegree = if (item.isExpanded) 90f else 0f
        ivArrow.rotation = rotateDegree

//        cbCheck.isChecked = item.isSelected

        if(item.isLeaf){
            cbCheck.isIndeterminate = false
            cbCheck.state = item.isSelected
        }else{
            when(treeView.checkNodeChildState(item.nodeId)){
                -1 -> {
                    cbCheck.isIndeterminate = false
                    item.isSelected = false
                    cbCheck.state = false
                }
                0 -> {
                    cbCheck.isIndeterminate = true
                    cbCheck.state = null
                }
                1 -> {
                    cbCheck.isIndeterminate = false
                    item.isSelected = true
                    cbCheck.state = true
                }
            }
        }

        //select node
        cbCheck.setOnClickListener {
            treeView.selectNode(item.nodeId, !item.isSelected) // will trigger onNodeSelected
        }

        //toggle node
        holder.setOnClickListener {
            if (item.isExpanded) {
                treeView.collapseNode(item.nodeId)
            } else {
                treeView.expandNode(item.nodeId)
            }
        }

        if((isShowLoading == false) && (position > 6)){
            isShowLoading = true
            dismissLoading()
        }
    }

    override fun onNodeSelected(node: NodeViewData<ProductCategoryModel>, child: List<NodeViewData<ProductCategoryModel>>, isSelected: Boolean) {
        //set selected for parent node and its child
        treeView.setSelectedNode(arrayListOf(node).apply { addAll(child) }, isSelected)

        //disable all child
        treeView.setNodesState(
            child.map { it.nodeId },
            if (isSelected) NodeStateDisabled else null
        )

        //update layout
        treeView.requestUpdateTree()
    }

    companion object {
        object NodeStateDisabled : NodeState()
    }

    override fun createObserver() {
        mViewModel.categoryList.observe(this){
            startBindData(it)
        }
    }

    override fun getToolbar() = mBinding.toolbar
}
```

# Need notice

When the data is too large, data conversion can take a long time. Therefore, it is best to display a progress bar during the conversion process when handling large datasets.