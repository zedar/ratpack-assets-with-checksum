package chksum

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import com.google.inject.Provides
import com.google.inject.Singleton
import javax.inject.Inject

import ratpack.file.FileSystemChecksumServices
import ratpack.server.ServerConfig
import ratpack.render.RenderableDecorator

import ratpack.groovy.template.MarkupTemplate

import com.github.jknack.handlebars.Handlebars;
import ratpack.handlebars.Template

import chksum.handlebars.AssetHelper
import chksum.AssetService

class ChecksumModule extends AbstractModule {
  Handlebars handlebars

  @Inject
  ChecksumModule(Handlebars handlebars) {
    super()
    this.handlebars = handlebars
  }
  
  @Override
  protected void configure() {
    System.out.println("===> CONFIGURE ChecksumModule")
    if (handlebars) {
      System.out.println("===> Handlebars is NOT NULL")
      handlebars.registerHelper(AssetHelper.NAME, AssetHelper.INSTANCE)
    }
  }

  @Provides
  @Singleton
  AssetService assetService(ServerConfig serverConfig) {
    return new AssetService(FileSystemChecksumServices.adler32(serverConfig, "public", "js", "css"))
  }

  @Provides
  RenderableDecorator<MarkupTemplate> provideGroovyMarkupTemplateRenderableDecorator(AssetService assetService) {
    RenderableDecorator.of(MarkupTemplate.class) { c, t ->
      new MarkupTemplate(t.name, t.contentType, [assets: assetService].plus(t.model))
    }
  }

  @Provides
  RenderableDecorator<Template> provideHandlebarsTemplateDecorator(AssetService assetService) {
    RenderableDecorator.of(Template.class) { c, t ->
      Template.handlebarsTemplate(t.getName(), assetService.convertTo((Map<String, Object>)t.getModel()), t.getContentType())
    }
  }

  @Provides
  RenderableDecorator<ratpack.thymeleaf.Template> provideThymeleafTemplateRenderableDecorator(AssetService assetService) {
    return RenderableDecorator.of(ratpack.thymeleaf.Template.class) {c, t -> 
      Map<String, Object> model = null;
      if (t.getModel() != null) {
        model = (Map<String, Object>)t.getModel().getVariables();
      }
      return ratpack.thymeleaf.Template.thymeleafTemplate(assetService.convertTo(model), t.getName(), t.getContentType());
    };
  }
}
