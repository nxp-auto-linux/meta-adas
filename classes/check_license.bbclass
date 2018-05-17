python () {
    lic_flags = (d.getVar('LICENSE_FLAGS_WHITELIST', True) or "").split()
    flag_set = False
    for flag in lic_flags:
        if flag == 'commercial':
            flag_set = True
    if not flag_set:
        bb.error("License ERROR: Please set the required license flag for FFMPEG\n\
            LICENSE_FLAGS_WHITELIST_append = \" commercial \" in conf/local.conf\n")
}
