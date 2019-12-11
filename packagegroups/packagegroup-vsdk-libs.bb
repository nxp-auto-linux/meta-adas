SUMMARY = "Required 3rd party packages for Vision SDK"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "packagegroup-vsdk-libs"

RDEPENDS_packagegroup-vsdk-libs = "\
	apex-drivers \
	cam-generic \
	oal-driver \
	jpegdec \
	viu \
	fdma \
	h264dec \
	csi \
	h264enc \
	sequencer \
	sm-drv \
"
