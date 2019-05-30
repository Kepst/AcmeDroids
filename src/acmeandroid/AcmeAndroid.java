/* 
 * Copyright (C) 2019 Wellington Regis, Pedro Stuginski, Kate Santos, Marcus Vinicius
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package acmeandroid;

/**
 * Driving class for the AcmeAndroid program
 * 
 * @author Wellington Regis
 * @author Pedro Stuginski
 * @author Kate Santos
 * @author Marcus Vinicius
 */
public class AcmeAndroid {

    /**
     * Main method
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Android android = new Android();
        Integer time = 0; //miliseconds
        
        System.out.println("-----Getting up-----");
        time = android.standUp(time);
        
        System.out.println("\n-----First step-----");
        time = android.firstStep(time);
        
        System.out.println("\n-----Walking-----");
        time = android.stepLeft(time);
        time = android.stepRight(time);
        
        System.out.println();
        System.out.println(android.showPositions());

    }
}
