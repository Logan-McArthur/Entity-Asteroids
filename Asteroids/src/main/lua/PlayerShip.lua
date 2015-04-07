function getDeclaredFunctions()
  return "constructEntity", "weaponFire"
end

function constructEntity( gameWorld )
  
  local entity = gameWorld:createBlankEntity("PlayerShip")
  
  local centerOffsetX = 10
  local centerOffsetY = 10
  local scaleX = 1.0
  local scaleY = 1.0
  
  local centerX = 40
  local centerY = 100
  local rotation = 0.0
  
  local mass = 1.0
  local moment = 1.0
  
  local velocityX = 0.0
  local velocityY = 0.0
  local velocityR = 0.0
  
  local drag = 0.95
  
  local health = 5
  
  local mountX = 22
  local mountY = 0
  
  local bulletVelF = 2
  local bulletVelS = 0
  
  local bulletLife = 1000
  
  local weaponCooldown = 1000
  
  local structure = gameWorld:constructStructure(gameWorld:getShipModel(), centerX, centerY, rotation, centerOffsetX, centerOffsetY, scaleX, scaleY)
  local physics = gameWorld:constructPhysics(structure, mass, moment, velocityX, velocityY, velocityR, drag)
  entity:addComponent( structure )
  entity:addComponent( physics )
  entity:addComponent( gameWorld:constructKeyboardInput())
  entity:addComponent( gameWorld:constructRender(structure))
  entity:addComponent( gameWorld:constructHealth(health))
  entity:addComponent( gameWorld:constructWeapon(physics, mountX, mountY, bulletVelF, bulletVelS, weaponCooldown, bulletLife))
  entity:addComponent( gameWorld:constructCountdown())
  entity:addComponent( gameWorld:constructAllegiance())
  
  
end

--	private void fireWeapon() {
--	float[] coords = body.getTransform().getPoint(1);
--	Bullet[] allocated = GameplayScreen.allocateBullets(currentWeapon.NUMBER_SHOTS);
--	for (int i = 0; i < allocated.length; i++) {
--		Body allocBod = allocated[i].body;
--		
--		allocBod.setPositionX(coords[0]);
--		allocBod.setPositionY(coords[1]);
--		allocBod.setVelocityX(body.getVelocityX());
--		allocBod.setVelocityY(body.getVelocityY());
--		allocBod.setRotation(body.getRotation() + currentWeapon.SHOT_ANGLES[i]);
--		allocated[i].start(currentWeapon.BULLET_LIFE);
-- --		allocated[i].recycle(coords[0], coords[1], body.velocityX, body.velocityY, body.rotation+currentWeapon.SHOT_ANGLES[i], currentWeapon.BULLET_LIFE);
--	}
--	
--	weaponCooler.start(currentWeapon.COOLDOWN);
--}

function weaponFire ( gameWorld, entity )
  print "firing lasers"
end
