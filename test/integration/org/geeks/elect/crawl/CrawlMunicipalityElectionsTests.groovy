package org.geeks.elect.crawl

import grails.test.*
import org.geeks.elect.District

class CrawlMunicipalityElectionsTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCrawl2007() {
      new CrawlMunicipalityElections().crawl2007Districts()
      assert District.count() == 60
    }
}
