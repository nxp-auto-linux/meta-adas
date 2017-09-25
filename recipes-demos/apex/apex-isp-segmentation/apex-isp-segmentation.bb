require recipes-demos/common-demos.inc
inherit insane

FILE_CHANGE_SA_TO_LINUX := "${EXTERNALSRC}/isp/graphs/rgb_yuv_gs8/build-v234ce-gnu-linux-d/Makefile"
EXTERNALSRC_BUILD := "${EXTERNALSRC}/demos/apex/apex_isp_segmentation/build-v234ce-gnu-linux-d"
INSANE_SKIP_${PN} += "build-deps"
