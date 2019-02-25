FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0ea90d28b4de883d7af5e6711f14f7bf"

inherit insane
inherit cmake

# We need to use a basic filepath here
SOURCES_PATH = "${@d.getVar('FSL_LOCAL_MIRROR', 1).replace('file://','')}"
EXTERNALSRC := "${SOURCES_PATH}/s32v234_sdk"
THRDPARTY_LIBS_DIR := "${EXTERNALSRC}/3rdparty"
OUTPUT_DIR_NAME = "usr"
INSTALL_DIR = "${D}/${OUTPUT_DIR_NAME}/lib"

SRC_URI = "https://github.com/opencv/opencv/archive/3.1.0.tar.gz \
	   file://0001-build-update-build-for-VSDK.patch \
	   file://fixgcc60.patch \
	   file://fixpkgconfig.patch \
"
SRC_URI[md5sum] = "a0669e22172dfc3225835b180744c9f0"
SRC_URI[sha256sum] = "f3b160b9213dd17aa15ddd45f6fb06017fe205359dbd1f7219aad59c98899f15"

S = "${WORKDIR}/opencv-3.1.0"

EXTRA_OECMAKE = "\
  -DWITH_VFW=OFF \
  -DWITH_GSTREAMER=OFF \
  -DWITH_UNICAP=OFF \
  -DWITH_PVAPI=OFF \
  -DWITH_GIGEAPI=OFF \
  -DWITH_1394=OFF \
  -DWITH_XINE=OFF \
  -DWITH_QT=OFF \
  -DWITH_V4L=OFF \
  -DWITH_OPENNI=OFF \
  -DWITH_XIMEA=OFF \
  -DWITH_FFMPEG=ON \
  -DFFMPEG_LIB_DIR=${THRDPARTY_LIBS_DIR}/ffmpeg/linux-arm64/lib \
  -DFFMPEG_INCLUDE_DIR=${THRDPARTY_LIBS_DIR}/ffmpeg/linux-arm64/include \
  -DWITH_DSHOW=OFF \
  -DWITH_MSMF=OFF \
  -DWITH_AVFOUNDATION=OFF \
  -DWITH_OPENCL=OFF \
  -DWITH_OPENCLAMDFFT=OFF \
  -DWITH_OPENCLAMDBLAS=OFF \
  -DWITH_PYTHON=OFF \
  -DWITH_NEON=OFF \
  -DENABLE_NEON=ON \
  -DWITH_GTK=OFF \
  -DBUILD_PERF_TESTS=OFF \
  -DBUILD_TESTS=OFF \
  -DBUILD_opencv_apps=OFF \
  -DCMAKE_BUILD_TYPE=RELEASE \
  -DCMAKE_SKIP_RPATH=ON \
  -DBUILD_ZLIB=ON \
"


do_install_append() {
  cp -rf ${D}/${OUTPUT_DIR_NAME}/* ${THRDPARTY_LIBS_DIR}/ocv/linux-arm64/
}

FILES_${PN} += "/usr/share/OpenCV/.cmake"
FILES_${PN} += "/usr/share/OpenCV/*.xml"
FILES_${PN} += "/${OUTPUT_DIR_NAME}"
FILES_${PN}-dbg += "/${OUTPUT_DIR_NAME}/.debug"

DEPENDS += "ffmpeg"
RDEPENDS_${PN} += "ffmpeg"
