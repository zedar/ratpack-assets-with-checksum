// Groovy Markup Template imports
import ratpack.groovy.template.TextTemplateModule
import ratpack.groovy.template.MarkupTemplateModule
import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack
import static ratpack.groovy.Groovy.groovyMarkupTemplate

// Handlebars Template imports
import ratpack.handlebars.HandlebarsModule
import static ratpack.handlebars.Template.handlebarsTemplate

// Thymeleaf Template imports
import ratpack.thymeleaf.ThymeleafModule
import static ratpack.thymeleaf.Template.thymeleafTemplate

ratpack {
  bindings {
    // bindings for Groovy Markup Templates
    add TextTemplateModule
    add new MarkupTemplateModule()

    // bindings for Handlebars Templates
    add new HandlebarsModule()

    // bindings for Thymeleaf Templates
    add new ThymeleafModule()
  }

  handlers {
    get {
      render groovyTemplate("index.html", title: "My Ratpack App")
    }
    prefix("index") {
      get("gtpl") {
        Map<String, Object> model = new HashMap<String, Object>()
        model.put("title", "TEST")
        render groovyMarkupTemplate(model, "index.gtpl")
      }
      
      get("hbs") {
        Map<String, Object> model = new HashMap<String, Object>()
        model.put("title", "TEST")
        render handlebarsTemplate(model, "index.html")
      }

      get("thymeleaf") {
        Map<String, Object> model = new HashMap<String, Object>()
        model.put("title", "TEST")
        render thymeleafTemplate(model, "index")
      }
    }
    assets "public"
  }
}
