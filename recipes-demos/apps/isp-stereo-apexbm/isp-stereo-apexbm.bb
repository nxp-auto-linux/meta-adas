require recipes-demos/common-demos.inc
inherit insane

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/apps/isp_stereo_apexbm/build-v234ce-gnu-linux-d"
DEPENDS += "ocv"
DEPENDS += "ffmpeg"
INSANE_SKIP_${PN} += "build-deps"
