# debug

This option defines globals that are usually used for logging poor-man's
debugging: `console`, `alert`, etc. It is usually a good idea to not
ship them in production because, for example, `console.log` breaks in
legacy versions of Internet Explorer.
