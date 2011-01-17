package org.geeks.elect

import grails.test.*
import org.geeks.elect.crawl.GetPage

class GetPageTests extends GrailsUnitTestCase {
  protected void setUp() {
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testGetUrl() {
    def content = new GetPage(url:"http://www.delfi.lt/").fetch()
    assertNotNull content
  }
}
