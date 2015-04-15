import pygame as pg
from AnimationObserver import AnimationObserver
from Animation import Animation

class dude(AnimationObserver):
	def __init__(self):
		self.ani = Animation(30,())
		self.ani.addImg('res/img/dudd.jpg')
		self.ani.addImg('res/img/dudd2.jpg')

	def notify(self, currentFps):
		self.ani.notify(currentFps)
		
	def seedFps(self, fps):
		self.ani.calcToken(fps)

	def draw(self, screen):
		self.ani.draw(self.screen, 50, 50)