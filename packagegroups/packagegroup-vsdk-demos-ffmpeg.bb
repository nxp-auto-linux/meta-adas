SUMMARY = "Required packages for Vision SDK"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "packagegroup-vsdk-demos-ffmpeg"

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
	lane-departure-warning \
	apex-isp-ldw-cv \
	isp-ov10635-viu-dcu \
	isp-stereo-apexbm \
	isp-stereo-calib \
	apex-isp-face-detection-cv \
	neon-gauss3x3-cv \
	h264-encoder-cv \
	web-server \
"
RDEPENDS_packagegroup-vsdk-demos-ffmpeg = "${DEMOS_FFMPEG}"
