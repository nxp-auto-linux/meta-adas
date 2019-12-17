require recipes-demos/common-demos.inc

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/other/h264_encoder_cv/build-v234ce-gnu-linux-d"
DEPENDS += "ocv"
DEPENDS += "ffmpeg"
DEPENDS += "boost"
