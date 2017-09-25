require recipes-demos/common-demos.inc

DEPENDS += "boost"
RDEPENDS_${PN} += "ffmpeg ocv"

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/other/web_server/build-v234ce-gnu-linux-d"

