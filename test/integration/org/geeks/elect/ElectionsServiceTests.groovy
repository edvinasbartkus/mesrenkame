package org.geeks.elect

import grails.test.*
import mesrenkame.ElectionsService

class ElectionsServiceTests extends GroovyTestCase {
	def service
	protected void setUp() {
		super.setUp()
		service = new ElectionsService()
	}

	protected void tearDown() {
			super.tearDown()
	}

	void testFindOrCreateDistrict() {
		District district = service.findOrCreateDistrict("This is a test")
		assert district.id

		District otherDistrict = service.findOrCreateDistrict("This is a test")
		assert otherDistrict.id
		assert district.id == otherDistrict.id
	}

	void testFindOrCreateElections() {
		Elections elections = service.findOrCreateElections("This is another test")
		assert elections.id

		Elections otherElections = service.findOrCreateElections("This is another test")
		assert otherElections.id
		assert elections.id == otherElections.id
	}
}
