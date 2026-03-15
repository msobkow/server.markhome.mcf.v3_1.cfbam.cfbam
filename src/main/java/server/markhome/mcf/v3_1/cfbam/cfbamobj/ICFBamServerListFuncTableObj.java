// Description: Java 25 Table Object interface for CFBam.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamServerListFuncTableObj
{
	public ICFBamSchemaObj getSchema();
	public void setSchema( ICFBamSchemaObj value );

	public void minimizeMemory();

	public String getTableName();
	public String getTableDbName();

	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	public int getClassCode();

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	// public static int getBackingClassCode();

	Class getObjQualifyingClass();

	/**
	 *	Instantiate a new ServerListFunc instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamServerListFuncObj newInstance();

	/**
	 *	Instantiate a new ServerListFunc edition of the specified ServerListFunc instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamServerListFuncEditObj newEditInstance( ICFBamServerListFuncObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamServerListFuncObj realiseServerListFunc( ICFBamServerListFuncObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamServerListFuncObj createServerListFunc( ICFBamServerListFuncObj Obj );

	/**
	 *	Read a ServerListFunc-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The ServerListFunc-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamServerListFuncObj readServerListFunc( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a ServerListFunc-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The ServerListFunc-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamServerListFuncObj readServerListFunc( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamServerListFuncObj readCachedServerListFunc( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeServerListFunc( ICFBamServerListFuncObj obj );

	void deepDisposeServerListFunc( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamServerListFuncObj lockServerListFunc( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the ServerListFunc-derived instances in the database.
	 *
	 *	@return	List of ICFBamServerListFuncObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamServerListFuncObj> readAllServerListFunc();

	/**
	 *	Return a sorted map of all the ServerListFunc-derived instances in the database.
	 *
	 *	@return	List of ICFBamServerListFuncObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamServerListFuncObj> readAllServerListFunc( boolean forceRead );

	List<ICFBamServerListFuncObj> readCachedAllServerListFunc();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamServerListFuncObj readServerListFuncByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamServerListFuncObj readServerListFuncByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerListFuncObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerListFuncObj> readServerListFuncByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamServerListFuncObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerListFuncObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerListFuncObj> readServerListFuncByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the CFBamServerMethodObj instance for the unique UNameIdx key.
	 *
	 *	@param	TableId	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@return	CFBamServerMethodObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamServerListFuncObj readServerListFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Get the CFBamServerMethodObj instance for the unique UNameIdx key.
	 *
	 *	@param	TableId	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@return	CFBamServerMethodObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamServerListFuncObj readServerListFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamServerMethodObj instances sorted by their primary keys for the duplicate MethTableIdx key.
	 *
	 *	@param	TableId	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerListFuncObj cached instances sorted by their primary keys for the duplicate MethTableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerListFuncObj> readServerListFuncByMethTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamServerListFuncObj instances sorted by their primary keys for the duplicate MethTableIdx key.
	 *
	 *	@param	TableId	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerListFuncObj cached instances sorted by their primary keys for the duplicate MethTableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerListFuncObj> readServerListFuncByMethTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamServerMethodObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerListFuncObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerListFuncObj> readServerListFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamServerListFuncObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerListFuncObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerListFuncObj> readServerListFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamServerListFuncObj instances sorted by their primary keys for the duplicate RetTblIdx key.
	 *
	 *	@param	RetTableId	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerListFuncObj cached instances sorted by their primary keys for the duplicate RetTblIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerListFuncObj> readServerListFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId );

	/**
	 *	Get the map of CFBamServerListFuncObj instances sorted by their primary keys for the duplicate RetTblIdx key.
	 *
	 *	@param	RetTableId	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerListFuncObj cached instances sorted by their primary keys for the duplicate RetTblIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerListFuncObj> readServerListFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId,
		boolean forceRead );

	ICFBamServerListFuncObj readCachedServerListFuncByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamServerListFuncObj> readCachedServerListFuncByTenantIdx( CFLibDbKeyHash256 TenantId );

	ICFBamServerListFuncObj readCachedServerListFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	List<ICFBamServerListFuncObj> readCachedServerListFuncByMethTableIdx( CFLibDbKeyHash256 TableId );

	List<ICFBamServerListFuncObj> readCachedServerListFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamServerListFuncObj> readCachedServerListFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId );

	void deepDisposeServerListFuncByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeServerListFuncByTenantIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposeServerListFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	void deepDisposeServerListFuncByMethTableIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeServerListFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeServerListFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId );

	/**
	 *	Internal use only.
	 */
	ICFBamServerListFuncObj updateServerListFunc( ICFBamServerListFuncObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteServerListFunc( ICFBamServerListFuncObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The ServerListFunc key attribute of the instance generating the id.
	 */
	void deleteServerListFuncByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The ServerListFunc key attribute of the instance generating the id.
	 */
	void deleteServerListFuncByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The ServerListFunc key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ServerListFunc key attribute of the instance generating the id.
	 */
	void deleteServerListFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The ServerListFunc key attribute of the instance generating the id.
	 */
	void deleteServerListFuncByMethTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The ServerListFunc key attribute of the instance generating the id.
	 */
	void deleteServerListFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	RetTableId	The ServerListFunc key attribute of the instance generating the id.
	 */
	void deleteServerListFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId );
}
