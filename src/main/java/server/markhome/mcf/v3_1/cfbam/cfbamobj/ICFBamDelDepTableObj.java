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

public interface ICFBamDelDepTableObj
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
	 *	Instantiate a new DelDep instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamDelDepObj newInstance();

	/**
	 *	Instantiate a new DelDep edition of the specified DelDep instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamDelDepEditObj newEditInstance( ICFBamDelDepObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamDelDepObj realiseDelDep( ICFBamDelDepObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamDelDepObj createDelDep( ICFBamDelDepObj Obj );

	/**
	 *	Read a DelDep-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DelDep-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDelDepObj readDelDep( CFLibDbKeyHash256 pkey );

	/**
	 *	Read a DelDep-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The DelDep-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamDelDepObj readDelDep( CFLibDbKeyHash256 pkey,
		boolean forceRead );

	ICFBamDelDepObj readCachedDelDep( CFLibDbKeyHash256 pkey );

	public void reallyDeepDisposeDelDep( ICFBamDelDepObj obj );

	void deepDisposeDelDep( CFLibDbKeyHash256 pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamDelDepObj lockDelDep( CFLibDbKeyHash256 pkey );

	/**
	 *	Return a sorted list of all the DelDep-derived instances in the database.
	 *
	 *	@return	List of ICFBamDelDepObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDelDepObj> readAllDelDep();

	/**
	 *	Return a sorted map of all the DelDep-derived instances in the database.
	 *
	 *	@return	List of ICFBamDelDepObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamDelDepObj> readAllDelDep( boolean forceRead );

	List<ICFBamDelDepObj> readCachedAllDelDep();

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DelDep key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelDepObj readDelDepByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Get the CFBamScopeObj instance for the primary key attributes.
	 *
	 *	@param	Id	The DelDep key attribute of the instance generating the id.
	 *
	 *	@return	CFBamScopeObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamDelDepObj readDelDepByIdIdx( CFLibDbKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the map of CFBamScopeObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The DelDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelDepObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelDepObj> readDelDepByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Get the map of CFBamDelDepObj instances sorted by their primary keys for the duplicate TenantIdx key.
	 *
	 *	@param	TenantId	The DelDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelDepObj cached instances sorted by their primary keys for the duplicate TenantIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelDepObj> readDelDepByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelDepObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DelDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelDepObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelDepObj> readDelDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamDelDepObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The DelDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelDepObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelDepObj> readDelDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamDelDepObj instances sorted by their primary keys for the duplicate DelDepIdx key.
	 *
	 *	@param	RelationId	The DelDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelDepObj cached instances sorted by their primary keys for the duplicate DelDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelDepObj> readDelDepByDelDepIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Get the map of CFBamDelDepObj instances sorted by their primary keys for the duplicate DelDepIdx key.
	 *
	 *	@param	RelationId	The DelDep key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamDelDepObj cached instances sorted by their primary keys for the duplicate DelDepIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamDelDepObj> readDelDepByDelDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead );

	ICFBamDelDepObj readCachedDelDepByIdIdx( CFLibDbKeyHash256 Id );

	List<ICFBamDelDepObj> readCachedDelDepByTenantIdx( CFLibDbKeyHash256 TenantId );

	List<ICFBamDelDepObj> readCachedDelDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	List<ICFBamDelDepObj> readCachedDelDepByDelDepIdx( CFLibDbKeyHash256 RelationId );

	void deepDisposeDelDepByIdIdx( CFLibDbKeyHash256 Id );

	void deepDisposeDelDepByTenantIdx( CFLibDbKeyHash256 TenantId );

	void deepDisposeDelDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	void deepDisposeDelDepByDelDepIdx( CFLibDbKeyHash256 RelationId );

	/**
	 *	Internal use only.
	 */
	ICFBamDelDepObj updateDelDep( ICFBamDelDepObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteDelDep( ICFBamDelDepObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The DelDep key attribute of the instance generating the id.
	 */
	void deleteDelDepByIdIdx( CFLibDbKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	TenantId	The DelDep key attribute of the instance generating the id.
	 */
	void deleteDelDepByTenantIdx( CFLibDbKeyHash256 TenantId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The DelDep key attribute of the instance generating the id.
	 */
	void deleteDelDepByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	RelationId	The DelDep key attribute of the instance generating the id.
	 */
	void deleteDelDepByDelDepIdx( CFLibDbKeyHash256 RelationId );
}
