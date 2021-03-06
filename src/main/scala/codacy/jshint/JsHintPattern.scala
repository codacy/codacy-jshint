package codacy.jshint

private[jshint] object JsHintPattern extends Enumeration {
  type JsHintPattern = Value

  val bitwise, camelcase, curly, eqeqeq, es3, forin, freeze, futurehostile, immed, indent, latedef, leanswitch, newcap,
  noarg, nocomma, noempty, noreturnawait, nonbsp, nonew, plusplus, quotmark, regexpu, undef, unused, strict, trailing,
  maxparams, maxdepth, maxstatements, maxcomplexity, maxlen, asi, boss, debug, eqnull, esnext, evil, expr, funcscope,
  gcl, globalstrict, iterator, lastsemic, laxbreak, laxcomma, loopfunc, maxerr, moz, multistr, notypeof, `null`, proto,
  scripturl, smarttabs, shadow, singleGroups, sub, supernew, trail, trailingcomma, validthis, `-W001`, `-W002`, `-W003`,
  `-W004`, `-W005`, `-W006`, `-W007`, `-W008`, `-W009`, `-W010`, `-W011`, `-W012`, `-W013`, `-W014`, `-W015`, `-W016`,
  `-W017`, `-W018`, `-W019`, `-W020`, `-W021`, `-W022`, `-W023`, `-W024`, `-W025`, `-W026`, `-W027`, `-W028`, `-W030`,
  `-W031`, `-W032`, `-W033`, `-W034`, `-W035`, `-W036`, `-W037`, `-W038`, `-W039`, `-W040`, `-W041`, `-W042`, `-W043`,
  `-W044`, `-W045`, `-W046`, `-W047`, `-W048`, `-W049`, `-W050`, `-W051`, `-W052`, `-W053`, `-W054`, `-W055`, `-W056`,
  `-W057`, `-W058`, `-W059`, `-W060`, `-W061`, `-W062`, `-W063`, `-W064`, `-W065`, `-W066`, `-W067`, `-W068`, `-W069`,
  `-W070`, `-W071`, `-W072`, `-W073`, `-W074`, `-W075`, `-W076`, `-W077`, `-W078`, `-W079`, `-W080`, `-W081`, `-W082`,
  `-W083`, `-W084`, `-W085`, `-W086`, `-W087`, `-W088`, `-W089`, `-W090`, `-W091`, `-W092`, `-W093`, `-W094`, `-W095`,
  `-W096`, `-W097`, `-W098`, `-W099`, `-W100`, `-W101`, `-W102`, `-W103`, `-W104`, `-W105`, `-W106`, `-W107`, `-W108`,
  `-W109`, `-W110`, `-W112`, `-W113`, `-W114`, `-W115`, `-W116`, `-W117`, `-W118`, `-W119`, `-W121`, `-W122`, `-W125`,
  `-W126`, `-W127`, `-W129`, `-W135`, `-W140`, `-W145`, `-W147`, `-E044`, browser, couch, devel, dojo, jquery, mootools,
  node, nonstandard, phantom, prototypejs, rhino, worker, wsh, yui =
    Value
}
