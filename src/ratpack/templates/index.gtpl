yieldUnescaped '''<!doctype html>
'''
html {
  head {
    meta(charset: "utf-8")
    title("Ratpack: " + title)

    link(rel: 'stylesheet', href: assets['lib/normalize/normalize.css?alpha=1'])
    link(rel: 'stylesheet', href: assets['styles/ratpack.css'])
    link(rel: 'stylesheet', href: assets['styles/style with spaces.css'])

    script(src: assets['lib/modernizr/modernizr.js']) {}
    script(src: assets['lib/prism/prism.js']) {}
  }
  body {
    header() {
      h1("Ratpack: " + title)
      p("Microservices framework")
    }
  }
}
