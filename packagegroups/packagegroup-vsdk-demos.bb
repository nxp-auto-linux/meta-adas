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

DEMOS_FFMPEG = "\
	apex-downsample-upsample-cv \
	apex-emulation-test \
	apex-face-detection-cv \
	apex-gauss5x5-cv \
	apex-indirect-input-cv \
	apex-integral-image-cv \
	apex-histogram-cv \
	apex-fast9-cv \
	apex-isp-fast9 \
	apex-roi-cv \
	feature-tracking \
	pedestrian-detection-aggcf \
	pedestrian-detection \
	cnn-classifier \
	lane-departure-warning \
	apex-isp-ldw-cv \
	isp-ov10635-viu-dcu \
	isp-stereo-apexbm \
	isp-stereo-calib \
	apex-isp-face-detection-cv \
	neon-gauss3x3-cv \
	h264-encoder-cv \
	web-server \
	avb-isp-h264-1stream \
	avb-isp-h264-4stream \
	avb-isp-jpeg-4stream \
"

RDEPENDS_packagegroup-vsdk-demos = "\
	${DEMOS_APEX_BASE} \
	${DEMOS_APEX} \
	${DEMOS_APEX_PRO} \
	${DEMOS_APPS} \
	${DEMOS_ISP} \
	${DEMOS_NEON} \
	${DEMOS_OTHER} \
	${DEMOS_FFMPEG} \
"
