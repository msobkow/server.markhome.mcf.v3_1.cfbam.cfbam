// Description: Java 25 Instance Edit Object interface for CFBam PopSubDep2.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamPopSubDep2EditObj
	extends ICFBamPopSubDep2Obj, ICFBamPopDepEditObj
{
	/*
	 *	Get the original for this edition cast as the specified type.
	 *
	 *	@return The original, non-modifiable instance cast to a ICFBamPopSubDep2Obj.
	 */
	ICFBamPopSubDep2Obj getOrigAsPopSubDep2();

	/**
	 *	Get the ICFBamPopSubDep1Obj instance referenced by the PopSubDep1 key.
	 *
	 *	@return	The ICFBamPopSubDep1Obj instance referenced by the PopSubDep1 key.
	 */
	ICFBamPopSubDep1Obj getRequiredContainerPopSubDep1();

	/**
	 *	Set the ICFBamPopSubDep1Obj instance referenced by the PopSubDep1 key.
	 *
	 *	@param	value	the ICFBamPopSubDep1Obj instance to be referenced by the PopSubDep1 key.
	 */
	void setRequiredContainerPopSubDep1( ICFBamPopSubDep1Obj value );

	/**
	 *	Get a list ICFBamPopSubDep3Obj instances referenced by the PopDep key.
	 *
	 *	@return	The (potentially empty) list of ICFBamPopSubDep3Obj instances referenced by the PopDep key.
	 */
	List<ICFBamPopSubDep3Obj> getOptionalComponentsPopDep();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute PopSubDep1Id.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute PopSubDep1Id.
	 */
	CFLibDbKeyHash256 getRequiredPopSubDep1Id();

	/**
	 *	Get the required String attribute Name.
	 *
	 *	@return	The required String attribute Name.
	 */
	String getRequiredName();

	/**
	 *	Set the required String attribute Name.
	 *
	 *	@param value The required String attribute Name value to be applied.
	 */
	void setRequiredName(String value);

}
