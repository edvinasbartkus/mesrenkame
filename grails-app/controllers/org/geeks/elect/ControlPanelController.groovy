package org.geeks.elect

import org.geeks.elect.crawl.CrawlMunicipalityElections

class ControlPanelController {
  def index = {

  }
  def crawl2007 = {

    def countAll = {  map ->
      [ Candidate, Declaration, Diploma, District, Elections, Party, PartyParticipation, Person ].each {
        def count = it.count()
        map[it.toString()] = count
      }
    }

    def before = [ : ]
    countAll(before)

    new CrawlMunicipalityElections().crawl2007()

    def after = [ : ]
    countAll(after)

    return [ before:before, after:after ]
  }
}
