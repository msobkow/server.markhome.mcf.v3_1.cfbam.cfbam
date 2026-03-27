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

public interface ICFBamTableTweakTableObj
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
	 *	Instantiate a new TableTweak instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamTableTweakObj newInstance();

	/**
	 *	Instantiate a new TableTweak edition of the specified TableTweak instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamTableTweakEditObj newEditInstance( ICFBamTableTweakObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamTableTweakObj realiseTableTweak( ICFBamTableTweakObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamTableTweakObj createTableTweak( ICFBamTableTweakObj Obj );

	/**
	 *	Read a TableTweak-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TableTweak-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTableTweakObj readTableTweak( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a TableTweak-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The TableTweak-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamTableTweakObj readTableTweak( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamTableTweakObj readCachedTableTweak( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeTableTweak( ICFBamTableTweakObj obj );

	void deepDisposeTableTweak( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamTableTweakObj lockTableTweak( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the TableTweak-derived instances in the database.
	 *
	 *	@return	List of ICFBamTableTweakObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTableTweakObj> readAllTableTweak();

	/**
	 *	Return a sorted map of all the TableTweak-derived instances in the database.
	 *
	 *	@return	List of ICFBamTableTweakObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamTableTweakObj> readAllTableTweak( boolean forceRead );

	List<ICFBamTableTweakObj> readCachedAllTableTweak();

	/**
	 *	Get the CFBamTweakObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@return	CFBamTweakObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTableTweakObj readTableTweakByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamTweakObj instance for the primary key attributes.
	 *
	 *	@param	Id	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@return	CFBamTweakObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamTableTweakObj readTableTweakByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamTweakObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@return	CFBamTweakObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTableTweakObj readTableTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamTweakObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@return	CFBamTweakObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamTableTweakObj readTableTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamTweakObj instances sorted by their primary keys for the duplicate ValTentIdx key.
	 *
	 *	@param	TenantId	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTableTweakObj cached instances sorted by their primary keys for the duplicate ValTentIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTableTweakObj> readTableTweakByValTentIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamTableTweakObj instances sorted by their primary keys for the duplicate ValTentIdx key.
	 *
	 *	@param	TenantId	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTableTweakObj cached instances sorted by their primary keys for the duplicate ValTentIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTableTweakObj> readTableTweakByValTentIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamTweakObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTableTweakObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTableTweakObj> readTableTweakByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamTableTweakObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTableTweakObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTableTweakObj> readTableTweakByScopeIdx( CFLibDbKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamTweakObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTableTweakObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTableTweakObj> readTableTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamTableTweakObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTableTweakObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTableTweakObj> readTableTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamTableTweakObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTableTweakObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTableTweakObj> readTableTweakByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Get the map of CFBamTableTweakObj instances sorted by their primary keys for the duplicate TableIdx key.
	 *
	 *	@param	TableId	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamTableTweakObj cached instances sorted by their primary keys for the duplicate TableIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamTableTweakObj> readTableTweakByTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead );

	ICFBamTableTweakObj readCachedTableTweakByIdIdx( CFLibDbKeyHash256 Id );

	ICFBamTableTweakObj readCachedTableTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	List<ICFBamTableTweakObj> readCachedTableTweakByValTentIdx( CFLibDbKeyHash256 TenantId );

	List<ICFBamTableTweakObj> readCachedTableTweakByScopeIdx( CFLibDbKeyHash256 ScopeId );

	List<ICFBamTableTweakObj> readCachedTableTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamTableTweakObj> readCachedTableTweakByTableIdx( CFLibDbKeyHash256 TableId );

	void deepDisposeTableTweakByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeTableTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	void deepDisposeTableTweakByValTentIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposeTableTweakByScopeIdx( CFLibDbKeyHash256 ScopeId );

	void deepDisposeTableTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeTableTweakByTableIdx( CFLibDbKeyHash256 TableId );

	/**
	 *	Internal use only.
	 */
	ICFBamTableTweakObj updateTableTweak( ICFBamTableTweakObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteTableTweak( ICFBamTableTweakObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The TableTweak key attribute of the instance generating the id.
	 */
	void deleteTableTweakByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TableTweak key attribute of the instance generating the id.
	 *
	 *	@param	Name	The TableTweak key attribute of the instance generating the id.
	 */
	void deleteTableTweakByUNameIdx( CFLibDbKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The TableTweak key attribute of the instance generating the id.
	 */
	void deleteTableTweakByValTentIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The TableTweak key attribute of the instance generating the id.
	 */
	void deleteTableTweakByScopeIdx( CFLibDbKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The TableTweak key attribute of the instance generating the id.
	 */
	void deleteTableTweakByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	TableId	The TableTweak key attribute of the instance generating the id.
	 */
	void deleteTableTweakByTableIdx( CFLibDbKeyHash256 TableId );
}
