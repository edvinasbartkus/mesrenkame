package org.geeks.elect.integration

import grails.test.*
import org.geeks.elect.crawl.GetPage
import org.geeks.elect.CachedPage

class GetPageTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testRedisSave() {
      def page = new GetPage(url:"http://www.delfi.lt/").get()
      assertNotNull page

      def count = CachedPage.count()
      assert count > 0
      new GetPage(url:"http://www.delfi.lt/").get()
      assertEquals CachedPage.count(), count
    }
}
