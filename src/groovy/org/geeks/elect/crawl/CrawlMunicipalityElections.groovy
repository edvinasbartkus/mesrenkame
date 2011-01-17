package org.geeks.elect.crawl

import mesrenkame.ElectionsService
import org.apache.commons.logging.LogFactory

class CrawlMunicipalityElections {
  private static final log = LogFactory.getLog(this)

  def crawl2007() {
    crawl2007Districts().each {
      def electionsService = new ElectionsService()
      def name = it[0]
      def link = it[1]

      electionsService.findOrCreateDistrict(name)
    }

  }

  def crawl2007Districts() {
    def doc = new GetPage(url:"http://www.vrk.lt/rinkimai/3/Kandidatai/index.html").parse()

    def urls = []
    doc.depthFirst().findAll {
      it.name() == "a" && it.@href =~ /DalyvaujanciosPartijos.html/
    }.each {
      log.info "Found district ${it.text()} with link to ${it.@href.toString()}"
      urls << [ it.text(), it.@href.toString() ]
    }

    return urls
  }
  def crawl2007Parties(String districtName, String url) {
    def doc = new GetPage(url:url).parse()
    doc.depthFirst().findAll {
      it.name() == "a" && it.@href =~ /Kandidatai.html/
    }.each {
      def partyName = it.text()
      def partyUrl = it.@href.toString()
    }
  }

  def crawl2007Candidates(String url) {
    def doc = new GetPage(url:url).parse()
    doc.depthFirst().findAll {
      it.name() == "a" && it.@href =~ /Anketa.html/
    }.each {

    }
  }
}
