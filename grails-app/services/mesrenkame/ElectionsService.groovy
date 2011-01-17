package mesrenkame

import org.geeks.elect.District

class ElectionsService {

  static transactional = false

  def findOrCreateDistrict(String name) {
    def district = District.findByName(name)
    if(!district) {
      district = new District(name:name)
      district.save(flush:true)
      log.debug "District not found. Saved!"
    } else {
      log.debug "District ${name} found."
    }
    return district
  }
}
