package mesrenkame

import org.geeks.elect.District

class ElectionsService {

  static transactional = true

  def findOrCreateDistrict(String name) {
    def district = District.findByName(name)
    if(!district) {
      district = new District(name:name)
      district.save(flush:true)
    }
    return district
  }
}
