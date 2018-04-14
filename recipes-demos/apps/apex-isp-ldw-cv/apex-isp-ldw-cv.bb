require recipes-demos/common-demos.inc
inherit insane

EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/apps/apex_isp_ldw_cv/build-v234ce-gnu-linux-d"
FILE_CHANGE_SA_TO_LINUX := "${EXTERNALSRC}/isp/graphs/imx224_exp_ctrl/build-v234ce-gnu-linux-d/Makefile"

DEPENDS += "ffmpeg"
INSANE_SKIP_${PN} += "build-deps file-rdeps ldflags"
RDEPENDS_${PN} += "boost"
