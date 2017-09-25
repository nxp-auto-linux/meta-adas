require recipes-demos/common-demos.inc
inherit insane

FILE_CHANGE_SA_TO_LINUX := "${EXTERNALSRC}/isp/graphs/sony_dualexp/build-v234ce-gnu-linux-d/Makefile"
EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/isp/isp_sonyimx224_default/build-v234ce-gnu-linux-d"
INSANE_SKIP_${PN} += "build-deps"
