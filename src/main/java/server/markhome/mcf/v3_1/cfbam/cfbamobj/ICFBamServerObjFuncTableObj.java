// Description: Java 25 Table Object interface for CFBam.

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
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamServerObjFuncTableObj
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
	 *	Instantiate a new ServerObjFunc instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamServerObjFuncObj newInstance();

	/**
	 *	Instantiate a new ServerObjFunc edition of the specified ServerObjFunc instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamServerObjFuncEditObj newEditInstance( ICFBamServerObjFuncObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamServerObjFuncObj realiseServerObjFunc( ICFBamServerObjFuncObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamServerObjFuncObj createServerObjFunc( ICFBamServerObjFuncObj Obj );

	/**
	 *	Read a ServerObjFunc-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The ServerObjFunc-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamServerObjFuncObj readServerObjFunc( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a ServerObjFunc-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The ServerObjFunc-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamServerObjFuncObj readServerObjFunc( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamServerObjFuncObj readCachedServerObjFunc( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeServerObjFunc( ICFBamServerObjFuncObj obj );

	void deepDisposeServerObjFunc( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamServerObjFuncObj lockServerObjFunc( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the ServerObjFunc-derived instances in the database.
	 *
	 *	@return	List of ICFBamServerObjFuncObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamServerObjFuncObj> readAllServerObjFunc();

	/**
	 *	Return a sorted map of all the ServerObjFunc-derived instances in the database.
	 *
	 *	@return	List of ICFBamServerObjFuncObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamServerObjFuncObj> readAllServerObjFunc( boolean forceRead );

	List<ICFBamServerObjFuncObj> readCachedAllServerObjFunc();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamServerObjFuncObj readServerObjFuncByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamServerObjFuncObj readServerObjFuncByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerObjFuncObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerObjFuncObj> readServerObjFuncByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamServerObjFuncObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerObjFuncObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerObjFuncObj> readServerObjFuncByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the CFBamServerMethodObj instance for the unique UNameIdx key.
	 *
	 *	@param	TableId	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@return	CFBamServerMethodObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamServerObjFuncObj readServerObjFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Get the CFBamServerMethodObj instance for the unique UNameIdx key.
	 *
	 *	@param	TableId	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@return	CFBamServerMethodObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamServerObjFuncObj readServerObjFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamServerMethodObj instances sorted by their primary keys for the duplicate MethTableIdx key.
	 *
	 *	@param	TableId	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerObjFuncObj cached instances sorted by their primary keys for the duplicate MethTableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerObjFuncObj> readServerObjFuncByMethTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamServerObjFuncObj instances sorted by their primary keys for the duplicate MethTableIdx key.
	 *
	 *	@param	TableId	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerObjFuncObj cached instances sorted by their primary keys for the duplicate MethTableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerObjFuncObj> readServerObjFuncByMethTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamServerMethodObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerObjFuncObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerObjFuncObj> readServerObjFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamServerObjFuncObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerObjFuncObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerObjFuncObj> readServerObjFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamServerObjFuncObj instances sorted by their primary keys for the duplicate RetTblIdx key.
	 *
	 *	@param	RetTableId	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerObjFuncObj cached instances sorted by their primary keys for the duplicate RetTblIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerObjFuncObj> readServerObjFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId );

	/**
	 *	Get the map of CFBamServerObjFuncObj instances sorted by their primary keys for the duplicate RetTblIdx key.
	 *
	 *	@param	RetTableId	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamServerObjFuncObj cached instances sorted by their primary keys for the duplicate RetTblIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamServerObjFuncObj> readServerObjFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId,
		boolean forceRead );

	ICFBamServerObjFuncObj readCachedServerObjFuncByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamServerObjFuncObj> readCachedServerObjFuncByTenantIdx( CFLibDbKeyHash256 TenantId );

	ICFBamServerObjFuncObj readCachedServerObjFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	List<ICFBamServerObjFuncObj> readCachedServerObjFuncByMethTableIdx( CFLibDbKeyHash256 TableId );

	List<ICFBamServerObjFuncObj> readCachedServerObjFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamServerObjFuncObj> readCachedServerObjFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId );

	void deepDisposeServerObjFuncByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeServerObjFuncByTenantIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposeServerObjFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	void deepDisposeServerObjFuncByMethTableIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeServerObjFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeServerObjFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId );

	/**
	 *	Internal use only.
	 */
	ICFBamServerObjFuncObj updateServerObjFunc( ICFBamServerObjFuncObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteServerObjFunc( ICFBamServerObjFuncObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The ServerObjFunc key attribute of the instance generating the id.
	 */
	void deleteServerObjFuncByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The ServerObjFunc key attribute of the instance generating the id.
	 */
	void deleteServerObjFuncByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The ServerObjFunc key attribute of the instance generating the id.
	 *
	 *	@param	Name	The ServerObjFunc key attribute of the instance generating the id.
	 */
	void deleteServerObjFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The ServerObjFunc key attribute of the instance generating the id.
	 */
	void deleteServerObjFuncByMethTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The ServerObjFunc key attribute of the instance generating the id.
	 */
	void deleteServerObjFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	RetTableId	The ServerObjFunc key attribute of the instance generating the id.
	 */
	void deleteServerObjFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId );
}
