DESCRIPTION = "Vision SDK demo applications"
LICENSE = "Freescale-EULA"
LIC_FILES_CHKSUM = "file://${FSL_EULA_FILE};md5=88a13f4f35506a1e6c6049ffb53cffe7"


inherit externalsrc
inherit check_license

# We need to use a basic filepath here
SOURCES_PATH = "${@d.getVar('FSL_LOCAL_MIRROR', 1).replace('file://','')}"

EXTERNALSRC := "${SOURCES_PATH}/s32v234_sdk"

# Default APU compiler options: no APU compiler, using the precompiled tct
# graphs from the VSDK release
APU_OPT_DEFAULT = "APU_COMP=tct APU_PRECOMP=1"

# Use TCT APU toolchain to compile the APU graphs.
APU_OPT_TCT = "APU_COMP=tct APU_TOOLS=${APU_COMPILER_TCT}"

# Use NXP APU toolchain to compile the APU graphs.
APU_OPT_NXP = "APU_COMP=nxp APU_TOOLS=${APU_COMPILER_NXP}"

# If the NXP/TCT APU toolchain install path is defined in conf/local.conf
# the graphs are compiled with this toolchain
# Else, the default precompiled tct graphs are used
APU_OPT = "${@d.getVar('APU_OPT_NXP', True) if d.getVar('APU_COMPILER_NXP', True) \
	 else d.getVar('APU_OPT_TCT', True) if d.getVar('APU_COMPILER_TCT', True) \
	 else d.getVar('APU_OPT_DEFAULT', True)}"

# In a typical project the developer will use vanilla VisionSDK libraries,
# e.g. System Libraries, without any changes as provided by the VisionSDK installer,
# but Yocto should build everything from scratch, so we change the default behavior
# to re-build System Libraries.
# The re-build of System Libraries can be enforced using the PRE_BUILT_SYSTEM_LIBS
# variable in conf/local.conf:
#   * PRE_BUILT_SYSTEM_LIBS=0 rebuild System Libraries (default)
#   * PRE_BUILT_SYSTEM_LIBS=1 use pre-built System Libraries
USE_CUSTOME_SYSTEM_LIBS = "PRE_BUILT_SYSTEM_LIBS=${PRE_BUILT_SYSTEM_LIBS}"
USE_DEFALT_SYSTEM_LIBS = "PRE_BUILT_SYSTEM_LIBS=0"
USE_SYSTEM_LIBS = "${@d.getVar('USE_CUSTOME_SYSTEM_LIBS', True) if d.getVar('PRE_BUILT_SYSTEM_LIBS', True) \
	else d.getVar('USE_DEFALT_SYSTEM_LIBS', True)}"

EXTRA_OEMAKE = "${APU_OPT} ${USE_SYSTEM_LIBS} V=1"

EXTRA_OEMAKE += "CROSS_COMPILE="${TARGET_PREFIX}""
EXTRA_OEMAKE += "CROSS_COMPILE_SYSROOT=--sysroot=\"${STAGING_DIR_TARGET}\""
EXTRA_OEMAKE += "CROSS_COMPILE_M0_ELF="${M0_TOOLCHAIN_DIR}/bin/arm-eabi-""

INSTALL_DIR = "${D}${VSDK_OUTPUT_DIR_NAME}"


python check_host_gcc() {
    import re, subprocess

    # VSDK build system requires a minimum version for HOST GCC
    MINIMUM_GCC = "4.9"

    compiler = d.getVar("BUILD_CC")
    try:
        env = os.environ.copy()
        env["PATH"] = d.getVar("PATH")
        output = subprocess.check_output("%s --version" % compiler, shell=True, env=env).decode("utf-8")
    except subprocess.CalledProcessError as e:
        bb.fatal("Error running %s --version: %s" % (compiler, e.output.decode("utf-8")))

    match = re.match(".* (\d\.\d)\.\d.*", output.split('\n')[0])
    version = match.group(1)

    from distutils.version import StrictVersion
    if StrictVersion(version) < StrictVersion(MINIMUM_GCC):
        bb.fatal("Host gcc version (%s) must be >= %s" % (version, MINIMUM_GCC))
}

do_configure[prefuncs] += "check_host_gcc"

do_install() {
	mkdir -p "${INSTALL_DIR}"
	cp ${EXTERNALSRC_BUILD}/*.elf "${INSTALL_DIR}/"
}

do_compile[lockfiles] = "${TMPDIR}/demos-scripts.lock"

do_compile () {
	make -C ${EXTERNALSRC_BUILD} ${EXTRA_OEMAKE} allsub
}

do_clean[postfuncs] += "do_clean_local"

do_clean_local () {
	make -C ${EXTERNALSRC_BUILD} ${APU_OPT} ${USE_SYSTEM_LIBS} cleansub
}

FILES_${PN} += "${VSDK_OUTPUT_DIR_NAME}"
FILES_${PN}-dbg += "${VSDK_OUTPUT_DIR_NAME}/.debug"
INSANE_SKIP_${PN} += "ldflags"
RDEPENDS_${PN} += "ocv"
DEPENDS_append = " dos2unix-native"
DEPENDS_append = " tcl-native"
DEPENDS_append = " m0-toolchain-native"

COMPATIBLE_MACHINE = "(s32v234.*)"
