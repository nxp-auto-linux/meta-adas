SUMMARY = "Required 3rd party packages for Vision SDK"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "packagegroup-vsdk-libs-3rdparty"

RDEPENDS_packagegroup-vsdk-libs-3rdparty = "\
	boost \
	ffmpeg \
	ocv \
"

DEPENDS_packagegroup-vsdk-libs-3rdparty = "fftw"
