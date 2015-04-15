import pygame as pg
from Collision 			import CollisionObserver
from AnimationObserver 	import AnimationObserver
from Animation 			import Animation
from DrawObserver 		import DrawObserver



class Block(CollisionObserver, AnimationObserver, DrawObserver):
	def __init__(self, x, y):
		self.w = 100
		self.h = 75
		self.x = x
		self.y = y
		self.rect = pg.Rect(self.x,self.y,self.w,self.h)
		self.animation = Animation(9,())
		self.animation.addImg('res/img/block.png')
		self.animation.addImg('res/img/block2.png')
		self.animation.addImg('res/img/block3.png')

	def draw(self, screen):
		self.animation.draw(screen, self.x, self.y)


	def collidePlayer(self, player):
		print "Block blocks Player"

	def collideProjectile(self, thing):
		print "Block blocks Projectile"

	def collideElse(self, thing):
		print "Block blocks!"

	def getRect(self):
		return self.rect

	def notify(self, currentFps):
		self.animation.notify(currentFps)
		

	def seedFps(self, fps):
		self.animation.calcToken(fps)
		
