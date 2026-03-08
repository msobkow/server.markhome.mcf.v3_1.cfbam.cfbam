
// Description: Java JPA Factory interface for RelationCol.

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
 *	ICFBamRelationColFactory interface for RelationCol
 */
public interface ICFBamRelationColFactory
{

	/**
	 *	Allocate a primary history key for RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	ICFBamRelationColHPKey newHPKey();

	/**
	 *	Allocate a UNameIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByUNameIdxKey newByUNameIdxKey();

	/**
	 *	Allocate a RelationIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByRelationIdxKey newByRelationIdxKey();

	/**
	 *	Allocate a DefSchemaIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByDefSchemaIdxKey newByDefSchemaIdxKey();

	/**
	 *	Allocate a FromColIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByFromColIdxKey newByFromColIdxKey();

	/**
	 *	Allocate a ToColIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByToColIdxKey newByToColIdxKey();

	/**
	 *	Allocate a PrevIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByPrevIdxKey newByPrevIdxKey();

	/**
	 *	Allocate a NextIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByNextIdxKey newByNextIdxKey();

	/**
	 *	Allocate a RelPrevIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByRelPrevIdxKey newByRelPrevIdxKey();

	/**
	 *	Allocate a RelNextIdx key over RelationCol instances.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColByRelNextIdxKey newByRelNextIdxKey();

	/**
	 *	Allocate a RelationCol interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationCol newRec();

	/**
	 *	Allocate a RelationCol history interface implementation.
	 *
	 *	@return	The new instance.
	 */
	public ICFBamRelationColH newHRec();

}
