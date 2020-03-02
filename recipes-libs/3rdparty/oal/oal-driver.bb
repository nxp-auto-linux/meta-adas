require recipes-libs/common-elkms.inc

# Make sure we have the kernel build dir available
do_configure[depends] += "virtual/kernel:do_shared_workdir"

EXTERNALSRC_BUILD := "${EXTERNALSRC}/3rdparty/oal"
EXTRA_OEMAKE += "KERNEL_DIR=${KBUILD_OUTPUT} \
  OBJDIR=build-v234ce-gnu-linux-d \
  ARCH=arm64 \
  OAL_BUILD_CONFIG=debug CDEFS='-DOAL_LOG_SUPPRESS_NOTE -DOAL_LOG_SUPPRESS_DEBUG -DOAL_LOG_SUPPRESS_WARNING -DOAL_LOG_SUPPRESS_ERROR' \
  FILTER=%linux-kernel/"

do_install() {
	install -d "${INSTALL_DIR}/"
	install -m 0644 ${EXTERNALSRC}/3rdparty/oal/libs/kernel/driver/build-linux-kernel/build-v234ce-gnu-linux-d/oal_driver.ko "${INSTALL_DIR}/"
}

do_clean[postfuncs] += "do_clean_local"

do_clean_local () {
        make -C ${EXTERNALSRC_BUILD} ${EXTRA_OEMAKE} clean
}
