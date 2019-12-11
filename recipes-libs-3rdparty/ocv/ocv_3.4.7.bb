FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6450921bb12a3133c8f5cb2a80343710"

inherit insane
inherit cmake

# We need to use a basic filepath here
SOURCES_PATH = "${@d.getVar('FSL_LOCAL_MIRROR', 1).replace('file://','')}"
EXTERNALSRC := "${SOURCES_PATH}/s32v234_sdk"
THRDPARTY_LIBS_DIR := "${EXTERNALSRC}/3rdparty"
OUTPUT_DIR_NAME = "usr"
INSTALL_DIR = "${D}/${OUTPUT_DIR_NAME}/lib"


SRC_URI = "https://github.com/opencv/opencv/archive/3.4.7.tar.gz \
           file://0001-build-update-build-for-VSDK.patch \
"

SRC_URI[md5sum] = "faa2b5950f8bee3f03118e600c74746a"
SRC_URI[sha256sum] = "ea743896a604a6ba1e1c1651ad42c97d0f90165debe9940811c7e0bdaa307526"

S = "${WORKDIR}/opencv-3.4.7"

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
  -DENABLE_NEON=OFF \
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
RDEPENDS_${PN} += "bash"
