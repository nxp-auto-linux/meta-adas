SUMMARY = "Required packages for Vision SDK"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "packagegroup-vsdk-demos"

RDEPENDS_packagegroup-vsdk-demos = "\
	apex-add \
	apex-basic \
	apex-downsample-upsample-cv \
	apex-histogram-cv \
	apex-indirect-input-cv \
	apex-isp-fast9 \
	apex-math-lib \
	apex-roi-cv \
	apex-threads \
	apexcv-basic \
	apexcv-camdisp \
	apexcv-simple \
	apexcv-threads \
	apexcv-camdisp-histequal \
	apexcv-orb \
	apexcv-remap \
	apex-isp-face-detection-cv \
	apex-isp-ldw-cv \
	feature-tracking \
	isp-flex-frame-input \
	isp-stereo-apexbm \
	lane-departure-warning \
	pedestrian-detection-aggcf \
	pedestrian-detection \
	isp-h264dec-single-stream \
	isp-jpeg-4stream \
	isp-mem-simple \
	isp-ov10635-quad \
	isp-ov10635-viu-s \
	isp-ov10640-mipi-s \
	isp-ov10640-quad \
	isp-sonyimx224-generic-encryptedkernel \
	isp-sonyimx224-graph-switch \
	isp-sonyimx224-graph-switch-encryptedkernel \
	isp-sonyimx224-h264enc \
	isp-sonyimx224-h264enc-encryptedkernel \
	neon-eigen-add \
	neon-gauss3x3-cv \
	h264-encoder-cv \
	hello \
	multi-thread \
	onlinetool-genertingm1tom5 \
	demos-data \
"
