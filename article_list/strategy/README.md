# Naming strategy



## Drawable

In the `drawable` folder, there are usually two types of data. `shape` and `svg`.

### shape

we can follow the naming strategy below, which is divided into two parts, separated by two `_`。

The first part consists of `shape_[fill color]_[corner size]` and the second part presents the border, including `[stroke color]_[stroke size]`.

```
shape:
	rr: rounded_rectangle
	r: rectangle
	o: oval
	l: line
	r: ring
fill color:
	red/gray(semantic color value)
corner size:
	lrt30:left right top coner size will be 30 rpx
	30: left right top bottom coner size will be 30rpx

stoke color:
	same to fill color
stroke size
	same to corner size
```

`rr_red_140.xml` means there is an rounded rectangle here which has red color and 140dp corner.

### svg

it recommended semantic description. such as `svg_box.xml`









