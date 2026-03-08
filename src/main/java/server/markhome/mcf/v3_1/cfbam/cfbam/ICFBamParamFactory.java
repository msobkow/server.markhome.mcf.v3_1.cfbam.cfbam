
// Description: Java JPA Factory interface for Param.

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
 *	ICFBamParamFactory interface for Param
 */
public interface ICFBamParamFactory
{

	/**
	 *	Allocate a primary history key for Param instances.
	 *
	 *	@return	The new instance.
	 */
	ICFBamParamHPKey newHPKey();

	/**
	 *	Allocate a UNameIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a ServerMethodIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByServerMethodIdxKey newByServerMethodIdxKey();

	/**
	 *	Allocate a DefSchemaIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByDefSchemaIdxKey newByDefSchemaIdxKey();

	/**
	 *	Allocate a ServerTypeIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByServerTypeIdxKey newByServerTypeIdxKey();

	/**
	 *	Allocate a PrevIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByPrevIdxKey newByPrevIdxKey();

	/**
	 *	Allocate a NextIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByNextIdxKey newByNextIdxKey();

	/**
	 *	Allocate a ContPrevIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByContPrevIdxKey newByContPrevIdxKey();

	/**
	 *	Allocate a ContNextIdx key over Param instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamByContNextIdxKey newByContNextIdxKey();

	/**
	 *	Allocate a Param interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParam newRec();

	/**
	 *	Allocate a Param history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamParamH newHRec();

}
