
// Description: Java JPA Factory interface for Value.

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

package server.markhome.mcf.v3_1.cfbam.cfbam;

import java.lang.reflect.*;
import java.net.*;
import java.rmi.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;

/*
 *	ICFBamValueFactory interface for Value
 */
public interface ICFBamValueFactory
{

	/**
	 *	Allocate a primary history key for Value instances.
	 *
	 *	@return	The new instance.
	 */
	ICFBamValueHPKey newHPKey();

	/**
	 *	Allocate a UNameIdx key over Value instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamValueByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a ScopeIdx key over Value instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamValueByScopeIdxKey newByScopeIdxKey();

	/**
	 *	Allocate a DefSchemaIdx key over Value instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamValueByDefSchemaIdxKey newByDefSchemaIdxKey();

	/**
	 *	Allocate a PrevIdx key over Value instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamValueByPrevIdxKey newByPrevIdxKey();

	/**
	 *	Allocate a NextIdx key over Value instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamValueByNextIdxKey newByNextIdxKey();

	/**
	 *	Allocate a ContPrevIdx key over Value instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamValueByContPrevIdxKey newByContPrevIdxKey();

	/**
	 *	Allocate a ContNextIdx key over Value instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamValueByContNextIdxKey newByContNextIdxKey();

	/**
	 *	Allocate a Value interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamValue newRec();

	/**
	 *	Allocate a Value history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamValueH newHRec();

}
