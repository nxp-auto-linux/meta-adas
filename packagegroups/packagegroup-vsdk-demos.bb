SUMMARY = "Required packages for Vision SDK"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "packagegroup-vsdk-demos"

DEMOS_APEX = "\
	apex-add \
	apex-isp-segmentation \
"

DEMOS_APEX_BASE = "\
	apexcv-basic \
	apexcv-simple \
"

DEMOS_APEX_PRO = "\
	apexcv-remap \
	apexcv-orb \
"

DEMOS_APPS = "\
	demos-data \
"

DEMOS_ISP = "\
	isp-h264dec-single-stream \
	isp-jpeg-4stream \
	isp-ov10640-quad \
	isp-sonyimx224-csi-dcu \
	isp-sonyimx224-default \
	isp-sonyimx224-rgb-yuv-gs8 \
	isp-sonyimx224-h264enc \
"

DEMOS_NEON = "\
	neon-eigen-add \
"

DEMOS_OTHER = "\
	hello \
	multi-thread \
"

RDEPENDS_packagegroup-vsdk-demos = "\
	${DEMOS_APEX_BASE} \
	${DEMOS_APEX} \
	${DEMOS_APEX_PRO} \
	${DEMOS_APPS} \
	${DEMOS_ISP} \
	${DEMOS_NEON} \
	${DEMOS_OTHER} \
"
