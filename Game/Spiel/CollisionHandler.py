import pygame as pg
from Collision import CollisionObserver

class CollisionHandler:
	def __init__ (self):
		self.observers 		= []
		self.projectiles 	= []
		self.players		= []

	# ADD-OBSERVER - PROJECTILES - PLAYERS
	def addPlayer(self, observer):
		self.players.append(observer)
		
	def addProjectile(self, observer):
		self.projectiles.append(observer)

	def addObserver(self,observer):
		self.observers.append(observer)
		
	#REMOVE STUFF
	def removePlayer(self, observer):
		self.players.remove(observer)

	def removeProjectile(self, observer):
		self.projectiles.remove(observer)

	def removeObserver(self,observer):
		self.observers.remove(observer)

	def checkCollision(self, collider_1 , collider_2):
		if collider_1.getRect().colliderect(collider_2.getRect()):
			return True
		else:
			return False

	# CHECK COLLISION WITH PROJECTILES
	def checkPCollision(self, collider_1):
		for projectile in self.projectiles:
			if(self.checkCollision(collider_1, projectile)):
				(collider_1).collideProjectile(projectile)
		
	#CHECK COLLISION WITH OTHERS
	def checkOCollision(self, collider_1):
		counter = 0
		for thing in self.observers:
			thing.getRect()
			if(self.checkCollision(collider_1, thing)):
				collider_1.collideElse(thing)


	#CHECK COLLISION WITH OTHER PLAYERS
	def checkUCollision(self, collider_1):
		for dudes in self.players:
			if(self.checkCollision(collider_1, dudes)):
				collider_1.collidePlayer(dudes)
		