package org.geeks.elect.crawl

import org.geeks.elect.CachedPage
import org.ccil.cowan.tagsoup.Parser

class GetPage {
  String url

  def get() {
    CachedPage redisPage = CachedPage.findByUrl(this.url)
    if(redisPage) {
      return redisPage.content
    } else {
      def content = fetch()
      def page = new CachedPage(url:this.url, content:content)
      page.save(flush:true)
      return content
    }
  }

  def fetch() {
    URL url = new URL(this.url)
    return url.text
  }

  def parse() {
    def slurper = new XmlSlurper(new Parser());
    return slurper.parseText(get())
  }
}
