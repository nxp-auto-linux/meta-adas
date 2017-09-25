SUMMARY = "Required packages for Vision SDK"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "packagegroup-fsl-adas-vsdk"

RDEPENDS_packagegroup-fsl-adas-vsdk = "\
	packagegroup-vsdk-demos \
	packagegroup-vsdk-libs \
	packagegroup-vsdk-libs-3rdparty \
"
