PROVIDES += "fftw"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# it actually depends on oal/user, but there is no individual oal/user recipe
# apex-add will build the oal/user dependency
DEPENDS += "apex-add"


# We need to use a basic filepath here
LOCAL_MIRROR_PATH = "${@d.getVar('FSL_LOCAL_MIRROR', 1).replace('file://','')}"

VSDK_LAYOUT_PATH := "${LOCAL_MIRROR_PATH}/s32v234_sdk"
THRDPARTY_LIBS_DIR := "${VSDK_LAYOUT_PATH}/3rdparty"
VSDK_FFTW_BUILD_DIR = "${THRDPARTY_LIBS_DIR}/${BPN}/build-v234ce-gnu-linux"

OUTPUT_DIR_NAME = "fftw/linux-arm64"
INSTALL_DIR = "${THRDPARTY_LIBS_DIR}/${OUTPUT_DIR_NAME}"

B = "${WORKDIR}/${BPN}-${PV}"

SRC_URI += "file://fftw_3_4_4.patch"


ARM_INCS="-I${VSDK_LAYOUT_PATH}/include"
ARM_LIBS_PATH="-L${VSDK_LAYOUT_PATH}/libs/utils/oal/user/build-v234ce-gnu-linux-d"
LIBS="-loal -lc -lg -lgcc -lstdc++"

PREFIX="--prefix=${INSTALL_DIR} --host=${TARGET_SYS} --enable-single --enable-neon"
PREFIX2="-mcpu=cortex-a53 -nostdlib -fno-builtin -fno-exceptions"

do_configure() {
	mkdir -p "${INSTALL_DIR}"
	./configure CPPFLAGS="${ARM_INCS}" LDFLAGS="${ARM_LIBS_PATH}" ${PREFIX} CC="${CC} ${PREFIX2}" LIBS="${LIBS}"
}

do_install() {
  make install

  install -d ${D}/usr
  cp -r ${INSTALL_DIR}/* ${D}/usr
  chrpath -d ${D}/usr/bin/fftwf-wisdom
}

# This component is used only as a build-time dependency
# Do not ship it in the final package
#FILES_${PN} += "/${OUTPUT_DIR_NAME}"
#FILES_${PN}-dbg += "/${OUTPUT_DIR_NAME}/.debug"

INSANE_SKIP_${PN} += "ldflags"
