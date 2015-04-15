import sys
import pygame as pg

class KeyboardHandler:
    def __init__(self, player, number):
        self.player = player
        #self.joysticks = []
        if(number == 1):
            self.joystick = pg.joystick.Joystick(0)
            self.joystick.init()
        else:
            self.joystick = pg.joystick.Joystick(1)
            self.joystick.init()
    def checkKeys(self, event):
       

        if event.type == pg.QUIT:
            pg.quit()
            sys.exit()
        if event.type == pg.KEYDOWN:
            if event.key == pg.K_a:
                self.player.setLeft(True)
                self.player.setRight(False)
            if event.key == pg.K_d:
                self.player.setRight(True)
                self.player.setLeft(False)
            if self.player.getCanJump() == True and event.key == pg.K_w:
                self.player.setUp(True)
        elif event.type == pg.KEYUP:
            if event.key == pg.K_a:
                self.player.setLeft(False)
            if event.key == pg.K_d:
                self.player.setRight(False)
        else:
            self.player.setLeft(False)
            self.player.setRight(False)
            if event.type == pg.JOYAXISMOTION:
                #print "Joystick '",self.joysticks[event.joy].get_name(),"' axis",event.axis,"motion."
                h_axis_pos = self.joystick.get_axis(0)
                #v_axis_pos = self.joysticks[0].get_axis(1)
                #f_axis_pos = self.joystick.get_axis(4)
                
                if(h_axis_pos < 0 and h_axis_pos < -0.45):
                    self.player.setLeft(True)
                if(h_axis_pos > 0 and h_axis_pos > 0.45):
                    self.player.setRight(True)

            if event.type == pg.JOYBUTTONDOWN:
                print "Button", 
                  