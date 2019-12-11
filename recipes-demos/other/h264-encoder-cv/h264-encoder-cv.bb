require recipes-demos/common-demos.inc
inherit insane

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/other/h264_encoder_cv/build-v234ce-gnu-linux-d"
DEPENDS += "ocv"
DEPENDS += "ffmpeg"
DEPENDS += "boost"
INSANE_SKIP_${PN} += "build-deps"
