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
import server.markhome.mcf.v3_1.cflib.keyhash.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfbam.cfbampub.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprot.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbampubobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamprotobj.*;

public interface ICFBamBoolDefTableObj
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
	 *	Instantiate a new BoolDef instance.
	 *
	 *	@return	A new instance.
	 */
	ICFBamBoolDefObj newInstance();

	/**
	 *	Instantiate a new BoolDef edition of the specified BoolDef instance.
	 *
	 *	@return	A new edition.
	 */
	ICFBamBoolDefEditObj newEditInstance( ICFBamBoolDefObj orig );

	/**
	 *	Internal use only.
	 */
	ICFBamBoolDefObj realiseBoolDef( ICFBamBoolDefObj Obj );

	/**
	 *	Internal use only.
	 */
	ICFBamBoolDefObj createBoolDef( ICFBamBoolDefObj Obj );

	/**
	 *	Read a BoolDef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The BoolDef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamBoolDefObj readBoolDef( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Read a BoolDef-derived instance by it's primary key.
	 *
	 *	@param	pkey	The primary key identifying the instance to read.
	 *
	 *	@return	The BoolDef-derived instance identified by the primary key,
	 *		or null if no such key value exists.
	 */
	ICFBamBoolDefObj readBoolDef( $implCommaIJavaOptAtomType$ pkey,
		boolean forceRead );

	ICFBamBoolDefObj readCachedBoolDef( $implCommaIJavaOptAtomType$ pkey );

	public void reallyDeepDisposeBoolDef( ICFBamBoolDefObj obj );

	void deepDisposeBoolDef( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Internal use only.
	 */
	ICFBamBoolDefObj lockBoolDef( $implCommaIJavaOptAtomType$ pkey );

	/**
	 *	Return a sorted list of all the BoolDef-derived instances in the database.
	 *
	 *	@return	List of ICFBamBoolDefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamBoolDefObj> readAllBoolDef();

	/**
	 *	Return a sorted map of all the BoolDef-derived instances in the database.
	 *
	 *	@return	List of ICFBamBoolDefObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	List<ICFBamBoolDefObj> readAllBoolDef( boolean forceRead );

	List<ICFBamBoolDefObj> readCachedAllBoolDef();

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamBoolDefObj readBoolDefByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Get the CFBamValueObj instance for the primary key attributes.
	 *
	 *	@param	Id	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the primary key, or
	 *		null if no such instance exists.
	 */
	ICFBamBoolDefObj readBoolDefByIdIdx( ICFLibKeyHash256 Id,
		boolean forceRead );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj cached instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamBoolDefObj readBoolDefByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Get the CFBamValueObj instance for the unique UNameIdx key.
	 *
	 *	@param	ScopeId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	CFBamValueObj refreshed instance for the unique UNameIdx key, or
	 *		null if no such instance exists.
	 */
	ICFBamBoolDefObj readBoolDefByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBoolDefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBoolDefObj> readBoolDefByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Get the map of CFBamBoolDefObj instances sorted by their primary keys for the duplicate ScopeIdx key.
	 *
	 *	@param	ScopeId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBoolDefObj cached instances sorted by their primary keys for the duplicate ScopeIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBoolDefObj> readBoolDefByScopeIdx( ICFLibKeyHash256 ScopeId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBoolDefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBoolDefObj> readBoolDefByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Get the map of CFBamBoolDefObj instances sorted by their primary keys for the duplicate DefSchemaIdx key.
	 *
	 *	@param	DefSchemaId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBoolDefObj cached instances sorted by their primary keys for the duplicate DefSchemaIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBoolDefObj> readBoolDefByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBoolDefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBoolDefObj> readBoolDefByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamBoolDefObj instances sorted by their primary keys for the duplicate PrevIdx key.
	 *
	 *	@param	PrevId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBoolDefObj cached instances sorted by their primary keys for the duplicate PrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBoolDefObj> readBoolDefByPrevIdx( ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBoolDefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBoolDefObj> readBoolDefByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamBoolDefObj instances sorted by their primary keys for the duplicate NextIdx key.
	 *
	 *	@param	NextId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBoolDefObj cached instances sorted by their primary keys for the duplicate NextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBoolDefObj> readBoolDefByNextIdx( ICFLibKeyHash256 NextId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBoolDefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBoolDefObj> readBoolDefByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Get the map of CFBamBoolDefObj instances sorted by their primary keys for the duplicate ContPrevIdx key.
	 *
	 *	@param	ScopeId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBoolDefObj cached instances sorted by their primary keys for the duplicate ContPrevIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBoolDefObj> readBoolDefByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId,
		boolean forceRead );

	/**
	 *	Get the map of CFBamValueObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBoolDefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBoolDefObj> readBoolDefByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Get the map of CFBamBoolDefObj instances sorted by their primary keys for the duplicate ContNextIdx key.
	 *
	 *	@param	ScopeId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@return	List of CFBamBoolDefObj cached instances sorted by their primary keys for the duplicate ContNextIdx key,
	 *		which may be an empty set.
	 */
	List<ICFBamBoolDefObj> readBoolDefByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId,
		boolean forceRead );

	ICFBamBoolDefObj readCachedBoolDefByIdIdx( ICFLibKeyHash256 Id );

	ICFBamBoolDefObj readCachedBoolDefByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	List<ICFBamBoolDefObj> readCachedBoolDefByScopeIdx( ICFLibKeyHash256 ScopeId );

	List<ICFBamBoolDefObj> readCachedBoolDefByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	List<ICFBamBoolDefObj> readCachedBoolDefByPrevIdx( ICFLibKeyHash256 PrevId );

	List<ICFBamBoolDefObj> readCachedBoolDefByNextIdx( ICFLibKeyHash256 NextId );

	List<ICFBamBoolDefObj> readCachedBoolDefByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	List<ICFBamBoolDefObj> readCachedBoolDefByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	void deepDisposeBoolDefByIdIdx( ICFLibKeyHash256 Id );

	void deepDisposeBoolDefByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	void deepDisposeBoolDefByScopeIdx( ICFLibKeyHash256 ScopeId );

	void deepDisposeBoolDefByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	void deepDisposeBoolDefByPrevIdx( ICFLibKeyHash256 PrevId );

	void deepDisposeBoolDefByNextIdx( ICFLibKeyHash256 NextId );

	void deepDisposeBoolDefByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	void deepDisposeBoolDefByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 */
	ICFBamBoolDefObj updateBoolDef( ICFBamBoolDefObj Obj );

	/**
	 *	Internal use only.
	 */
	void deleteBoolDef( ICFBamBoolDefObj Obj );

	/**
	 *	Internal use only.
	 *
	 *	@param	Id	The BoolDef key attribute of the instance generating the id.
	 */
	void deleteBoolDefByIdIdx( ICFLibKeyHash256 Id );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@param	Name	The BoolDef key attribute of the instance generating the id.
	 */
	void deleteBoolDefByUNameIdx( ICFLibKeyHash256 ScopeId,
		String Name );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BoolDef key attribute of the instance generating the id.
	 */
	void deleteBoolDefByScopeIdx( ICFLibKeyHash256 ScopeId );

	/**
	 *	Internal use only.
	 *
	 *	@param	DefSchemaId	The BoolDef key attribute of the instance generating the id.
	 */
	void deleteBoolDefByDefSchemaIdx( ICFLibKeyHash256 DefSchemaId );

	/**
	 *	Internal use only.
	 *
	 *	@param	PrevId	The BoolDef key attribute of the instance generating the id.
	 */
	void deleteBoolDefByPrevIdx( ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	NextId	The BoolDef key attribute of the instance generating the id.
	 */
	void deleteBoolDefByNextIdx( ICFLibKeyHash256 NextId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@param	PrevId	The BoolDef key attribute of the instance generating the id.
	 */
	void deleteBoolDefByContPrevIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 PrevId );

	/**
	 *	Internal use only.
	 *
	 *	@param	ScopeId	The BoolDef key attribute of the instance generating the id.
	 *
	 *	@param	NextId	The BoolDef key attribute of the instance generating the id.
	 */
	void deleteBoolDefByContNextIdx( ICFLibKeyHash256 ScopeId,
		ICFLibKeyHash256 NextId );

	/**
	 *	Move the CFBamBoolDefObj instance up in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamBoolDefObj refreshed cache instance.
	 */
	ICFBamBoolDefObj moveUpBoolDef( ICFBamBoolDefObj Obj );

	/**
	 *	Move the CFBamBoolDefObj instance down in the chain.  The instance is always refreshed.
	 *
	 *	@return	CFBamBoolDefObj refreshed cache instance.
	 */
	ICFBamBoolDefObj moveDownBoolDef( ICFBamBoolDefObj Obj );
}
