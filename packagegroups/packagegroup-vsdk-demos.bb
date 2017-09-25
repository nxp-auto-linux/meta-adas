SUMMARY = "Required packages for Vision SDK"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "packagegroup-vsdk-demos"

DEMOS_APEX = "\
	apex-add \
	apex-downsample-upsample-cv \
	apex-emulation-test \
	apex-fast9-cv \
	apex-gauss5x5-cv \
	apex-histogram-cv \
	apex-indirect-input-cv \
	apex-integral-image-cv \
	apex-isp-fast9 \
	apex-roi-cv \
	apex-face-detection-cv \
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
	isp-stereo-apexbm \
	isp-stereo-calib \
	feature-tracking \
	pedestrian-detection-aggcf \
	pedestrian-detection \
	cnn-classifier \
	lane-departure-warning \
	apex-isp-ldw-cv \
	apex-isp-face-detection-cv \
"

DEMOS_ISP = "\
	isp-h264dec-single-stream \
	isp-jpeg-4stream \
	isp-ov10640-quad \
	isp-ov10635-viu-dcu \
	isp-sonyimx224-csi-dcu \
	isp-sonyimx224-default \
	isp-sonyimx224-rgb-yuv-gs8 \
	isp-sonyimx224-h264enc \
"

DEMOS_NEON = "\
	neon-eigen-add \
	neon-gauss3x3-cv \
	neon-fftw-cv \
"

DEMOS_OTHER = "\
	h264-encoder-cv \
	hello \
	multi-thread \
	web-server \
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

