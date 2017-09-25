require recipes-demos/common-demos.inc

DEPENDS += "boost"
RDEPENDS_${PN} += "ffmpeg ocv"

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/other/h264_encoder_cv/build-v234ce-gnu-linux-d"

