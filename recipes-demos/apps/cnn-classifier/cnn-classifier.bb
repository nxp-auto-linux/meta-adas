require recipes-demos/common-demos.inc
inherit insane

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/apps/cnn_classifier/build-v234ce-gnu-linux-d"
DEPENDS += "ffmpeg"
INSANE_SKIP_${PN} += "build-deps"
