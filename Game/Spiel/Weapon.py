import pygame as pg
from Collision 			import CollisionObserver
from AnimationObserver 	import AnimationObserver
from Animation 			import Animation
from DrawObserver 		import DrawObserver



class Weapon(CollisionObserver, AnimationObserver, DrawObserver):
	def __init__(self, x, y):
		self.w = 100
		self.h = 75
		self.x = x
		self.y = y
		self.rect = pg.Rect(self.x,self.y,self.w,self.h)
		self.animationIdleRight  = Animation(9, ('res/img/weapons/dagger/idle_r_1.png','res/img/weapons/dagger/idle_r_2.png'))
		self.animationIdleLeft  = Animation(9, ('res/img/weapons/dagger/idle_l_1.png','res/img/weapons/dagger/idle_l_2.png'))
		self.idle = (self.animationIdleRight, self.animationIdleLeft)
		self.direction = 0
		self.attacking = False
		self.angle	= 0
		# ADD
		# list with attakinganimations (1 per angle)
	def moveWeapon(self, x, y):
		self.x = x
		self.y = y

	def changeDir(self, direction):
		self.direction = direction

	def changeAngle(self, angle):
		self.angle = angle

	def changeStance(self, stance):
		if attacking:
			self.attacking = False
		else:
			self.attacking = True

	def draw(self, screen):
		if self.attacking:
			self.currentAnimation = self.attack[self.angle] 
		else:
			self.currentAnimation = self.idle[self.direction]
		self.currentAnimation.draw(screen, self.x, self.y)


	def collidePlayer(self, player):
		print "Block blocks Player"

	def collideProjectile(self, thing):
		print "Block blocks Projectile"

	def collideElse(self, thing):
		print "Block blocks!"

	def getRect(self):
		return self.rect

	def notify(self, currentFps):
		self.idle[self.direction].notify(currentFps)
		

	def seedFps(self, fps):
		for a in self.idle:
			a.calcToken(fps)
		return


		
		
