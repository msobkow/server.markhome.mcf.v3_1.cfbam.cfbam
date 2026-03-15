// Description: Java 25 Table Object implementation for DelSubDep2.

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

public class CFBamDelSubDep2TableObj
	implements ICFBamDelSubDep2TableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamDelSubDep2.CLASS_CODE;
	protected static final int backingClassCode = ICFBamDelSubDep2.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> members;
	private Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> allDelSubDep2;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > > indexByTenantIdx;
	private Map< ICFBamDelDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > > indexByDefSchemaIdx;
	private Map< ICFBamDelDepByDelDepIdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > > indexByDelDepIdx;
	private Map< ICFBamDelSubDep2ByContDelDep1IdxKey,
		Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > > indexByContDelDep1Idx;
	private Map< ICFBamDelSubDep2ByUNameIdxKey,
		ICFBamDelSubDep2Obj > indexByUNameIdx;
	public static String TABLE_NAME = "DelSubDep2";
	public static String TABLE_DBNAME = "delsubdep2";

	public CFBamDelSubDep2TableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDelSubDep2Obj>();
		allDelSubDep2 = null;
		indexByTenantIdx = null;
		indexByDefSchemaIdx = null;
		indexByDelDepIdx = null;
		indexByContDelDep1Idx = null;
		indexByUNameIdx = null;
	}

	public CFBamDelSubDep2TableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamDelSubDep2Obj>();
		allDelSubDep2 = null;
		indexByTenantIdx = null;
		indexByDefSchemaIdx = null;
		indexByDelDepIdx = null;
		indexByContDelDep1Idx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamDelSubDep2TableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamDelSubDep2TableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allDelSubDep2 = null;
		indexByTenantIdx = null;
		indexByDefSchemaIdx = null;
		indexByDelDepIdx = null;
		indexByContDelDep1Idx = null;
		indexByUNameIdx = null;
		List<ICFBamDelSubDep2Obj> toForget = new LinkedList<ICFBamDelSubDep2Obj>();
		ICFBamDelSubDep2Obj cur = null;
		Iterator<ICFBamDelSubDep2Obj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamDelSubDep2Obj.
	 */
	@Override
	public ICFBamDelSubDep2Obj newInstance() {
		ICFBamDelSubDep2Obj inst = new CFBamDelSubDep2Obj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamDelSubDep2Obj.
	 */
	@Override
	public ICFBamDelSubDep2EditObj newEditInstance( ICFBamDelSubDep2Obj orig ) {
		ICFBamDelSubDep2EditObj edit = new CFBamDelSubDep2EditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamDelSubDep2Obj realiseDelSubDep2( ICFBamDelSubDep2Obj Obj ) {
		ICFBamDelSubDep2Obj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDelSubDep2Obj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamDelSubDep2Obj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByDelDepIdx != null ) {
				ICFBamDelDepByDelDepIdxKey keyDelDepIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
				keyDelDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > mapDelDepIdx = indexByDelDepIdx.get( keyDelDepIdx );
				if( mapDelDepIdx != null ) {
					indexByDelDepIdx.remove( keyDelDepIdx );
				}
			}

			if( indexByContDelDep1Idx != null ) {
				ICFBamDelSubDep2ByContDelDep1IdxKey keyContDelDep1Idx =
					schema.getCFBamBackingStore().getFactoryDelSubDep2().newByContDelDep1IdxKey();
				keyContDelDep1Idx.setRequiredDelSubDep1Id( keepObj.getRequiredDelSubDep1Id() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > mapContDelDep1Idx = indexByContDelDep1Idx.get( keyContDelDep1Idx );
				if( mapContDelDep1Idx != null ) {
					mapContDelDep1Idx.remove( keepObj.getPKey() );
					if( mapContDelDep1Idx.size() <= 0 ) {
						indexByContDelDep1Idx.remove( keyContDelDep1Idx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamDelSubDep2ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryDelSubDep2().newByUNameIdxKey();
				keyUNameIdx.setRequiredDelSubDep1Id( keepObj.getRequiredDelSubDep1Id() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamDelSubDep2Obj)schema.getDelDepTableObj().realiseDelDep( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDelDepIdx != null ) {
				ICFBamDelDepByDelDepIdxKey keyDelDepIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
				keyDelDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > mapDelDepIdx = indexByDelDepIdx.get( keyDelDepIdx );
				if( mapDelDepIdx != null ) {
					mapDelDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContDelDep1Idx != null ) {
				ICFBamDelSubDep2ByContDelDep1IdxKey keyContDelDep1Idx =
					schema.getCFBamBackingStore().getFactoryDelSubDep2().newByContDelDep1IdxKey();
				keyContDelDep1Idx.setRequiredDelSubDep1Id( keepObj.getRequiredDelSubDep1Id() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > mapContDelDep1Idx = indexByContDelDep1Idx.get( keyContDelDep1Idx );
				if( mapContDelDep1Idx != null ) {
					mapContDelDep1Idx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamDelSubDep2ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryDelSubDep2().newByUNameIdxKey();
				keyUNameIdx.setRequiredDelSubDep1Id( keepObj.getRequiredDelSubDep1Id() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allDelSubDep2 != null ) {
				allDelSubDep2.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamDelSubDep2Obj)schema.getDelDepTableObj().realiseDelDep( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allDelSubDep2 != null ) {
				allDelSubDep2.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamDelDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDelDepIdx != null ) {
				ICFBamDelDepByDelDepIdxKey keyDelDepIdx =
					schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
				keyDelDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > mapDelDepIdx = indexByDelDepIdx.get( keyDelDepIdx );
				if( mapDelDepIdx != null ) {
					mapDelDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByContDelDep1Idx != null ) {
				ICFBamDelSubDep2ByContDelDep1IdxKey keyContDelDep1Idx =
					schema.getCFBamBackingStore().getFactoryDelSubDep2().newByContDelDep1IdxKey();
				keyContDelDep1Idx.setRequiredDelSubDep1Id( keepObj.getRequiredDelSubDep1Id() );
				Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj > mapContDelDep1Idx = indexByContDelDep1Idx.get( keyContDelDep1Idx );
				if( mapContDelDep1Idx != null ) {
					mapContDelDep1Idx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamDelSubDep2ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryDelSubDep2().newByUNameIdxKey();
				keyUNameIdx.setRequiredDelSubDep1Id( keepObj.getRequiredDelSubDep1Id() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamDelSubDep2Obj createDelSubDep2( ICFBamDelSubDep2Obj Obj ) {
		ICFBamDelSubDep2Obj obj = Obj;
		ICFBamDelSubDep2 rec = obj.getDelSubDep2Rec();
		schema.getCFBamBackingStore().getTableDelSubDep2().createDelSubDep2(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamDelSubDep2Obj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamDelSubDep2Obj readDelSubDep2( CFLibDbKeyHash256 pkey ) {
		return( readDelSubDep2( pkey, false ) );
	}

	@Override
	public ICFBamDelSubDep2Obj readDelSubDep2( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamDelSubDep2Obj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamDelSubDep2 readRec = schema.getCFBamBackingStore().getTableDelSubDep2().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamDelSubDep2Obj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamDelSubDep2Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamDelSubDep2Obj readCachedDelSubDep2( CFLibDbKeyHash256 pkey ) {
		ICFBamDelSubDep2Obj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeDelSubDep2( ICFBamDelSubDep2Obj obj )
	{
		final String S_ProcName = "CFBamDelSubDep2TableObj.reallyDeepDisposeDelSubDep2() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamDelSubDep2Obj existing = readCachedDelSubDep2( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamDelSubDep2ByContDelDep1IdxKey keyContDelDep1Idx = schema.getCFBamBackingStore().getFactoryDelSubDep2().newByContDelDep1IdxKey();
		keyContDelDep1Idx.setRequiredDelSubDep1Id( existing.getRequiredDelSubDep1Id() );

		ICFBamDelSubDep2ByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryDelSubDep2().newByUNameIdxKey();
		keyUNameIdx.setRequiredDelSubDep1Id( existing.getRequiredDelSubDep1Id() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );


		schema.getDelSubDep3TableObj().deepDisposeDelSubDep3ByDelSubDep2Idx( existing.getRequiredId() );

		if( indexByContDelDep1Idx != null ) {
			if( indexByContDelDep1Idx.containsKey( keyContDelDep1Idx ) ) {
				indexByContDelDep1Idx.get( keyContDelDep1Idx ).remove( pkey );
				if( indexByContDelDep1Idx.get( keyContDelDep1Idx ).size() <= 0 ) {
					indexByContDelDep1Idx.remove( keyContDelDep1Idx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


		schema.getDelDepTableObj().reallyDeepDisposeDelDep( obj );
	}
	@Override
	public void deepDisposeDelSubDep2( CFLibDbKeyHash256 pkey ) {
		ICFBamDelSubDep2Obj obj = readCachedDelSubDep2( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamDelSubDep2Obj lockDelSubDep2( CFLibDbKeyHash256 pkey ) {
		ICFBamDelSubDep2Obj locked = null;
		ICFBamDelSubDep2 lockRec = schema.getCFBamBackingStore().getTableDelSubDep2().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamDelSubDep2Obj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamDelSubDep2Obj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockDelSubDep2", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readAllDelSubDep2() {
		return( readAllDelSubDep2( false ) );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readAllDelSubDep2( boolean forceRead ) {
		final String S_ProcName = "readAllDelSubDep2";
		if( ( allDelSubDep2 == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> map = new HashMap<CFLibDbKeyHash256,ICFBamDelSubDep2Obj>();
			allDelSubDep2 = map;
			ICFBamDelSubDep2[] recList = schema.getCFBamBackingStore().getTableDelSubDep2().readAllDerived( null );
			ICFBamDelSubDep2 rec;
			ICFBamDelSubDep2Obj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelSubDep2Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelSubDep2Obj realised = (ICFBamDelSubDep2Obj)obj.realise();
			}
		}
		int len = allDelSubDep2.size();
		ICFBamDelSubDep2Obj arr[] = new ICFBamDelSubDep2Obj[len];
		Iterator<ICFBamDelSubDep2Obj> valIter = allDelSubDep2.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelSubDep2Obj> arrayList = new ArrayList<ICFBamDelSubDep2Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelSubDep2Obj> cmp = new Comparator<ICFBamDelSubDep2Obj>() {
			@Override
			public int compare( ICFBamDelSubDep2Obj lhs, ICFBamDelSubDep2Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelSubDep2Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readCachedAllDelSubDep2() {
		final String S_ProcName = "readCachedAllDelSubDep2";
		ArrayList<ICFBamDelSubDep2Obj> arrayList = new ArrayList<ICFBamDelSubDep2Obj>();
		if( allDelSubDep2 != null ) {
			int len = allDelSubDep2.size();
			ICFBamDelSubDep2Obj arr[] = new ICFBamDelSubDep2Obj[len];
			Iterator<ICFBamDelSubDep2Obj> valIter = allDelSubDep2.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamDelSubDep2Obj> cmp = new Comparator<ICFBamDelSubDep2Obj>() {
			public int compare( ICFBamDelSubDep2Obj lhs, ICFBamDelSubDep2Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamDelSubDep2Obj readDelSubDep2ByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readDelSubDep2ByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamDelSubDep2Obj readDelSubDep2ByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamDelSubDep2Obj obj = readDelSubDep2( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readDelSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readDelSubDep2ByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readDelSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelSubDep2ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep2Obj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelSubDep2Obj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelSubDep2Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelSubDep2Obj realised = (ICFBamDelSubDep2Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelSubDep2Obj arr[] = new ICFBamDelSubDep2Obj[len];
		Iterator<ICFBamDelSubDep2Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelSubDep2Obj> arrayList = new ArrayList<ICFBamDelSubDep2Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelSubDep2Obj> cmp = new Comparator<ICFBamDelSubDep2Obj>() {
			@Override
			public int compare( ICFBamDelSubDep2Obj lhs, ICFBamDelSubDep2Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelSubDep2Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readDelSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readDelSubDep2ByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readDelSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelSubDep2ByDefSchemaIdx";
		ICFBamDelDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamDelDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep2Obj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelSubDep2Obj>();
			ICFBamDelDepObj obj;
			ICFBamDelDep[] recList = schema.getCFBamBackingStore().getTableDelDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamDelDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelSubDep2Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelSubDep2Obj realised = (ICFBamDelSubDep2Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelSubDep2Obj arr[] = new ICFBamDelSubDep2Obj[len];
		Iterator<ICFBamDelSubDep2Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelSubDep2Obj> arrayList = new ArrayList<ICFBamDelSubDep2Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelSubDep2Obj> cmp = new Comparator<ICFBamDelSubDep2Obj>() {
			@Override
			public int compare( ICFBamDelSubDep2Obj lhs, ICFBamDelSubDep2Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelSubDep2Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readDelSubDep2ByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readDelSubDep2ByDelDepIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readDelSubDep2ByDelDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readDelSubDep2ByDelDepIdx";
		ICFBamDelDepByDelDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> dict;
		if( indexByDelDepIdx == null ) {
			indexByDelDepIdx = new HashMap< ICFBamDelDepByDelDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep2Obj > >();
		}
		if( ( ! forceRead ) && indexByDelDepIdx.containsKey( key ) ) {
			dict = indexByDelDepIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelSubDep2Obj>();
			ICFBamDelDepObj obj;
			ICFBamDelDep[] recList = schema.getCFBamBackingStore().getTableDelDep().readDerivedByDelDepIdx( null,
				RelationId );
			ICFBamDelDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelSubDep2Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelSubDep2Obj realised = (ICFBamDelSubDep2Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDelDepIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelSubDep2Obj arr[] = new ICFBamDelSubDep2Obj[len];
		Iterator<ICFBamDelSubDep2Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelSubDep2Obj> arrayList = new ArrayList<ICFBamDelSubDep2Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelSubDep2Obj> cmp = new Comparator<ICFBamDelSubDep2Obj>() {
			@Override
			public int compare( ICFBamDelSubDep2Obj lhs, ICFBamDelSubDep2Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelSubDep2Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readDelSubDep2ByContDelDep1Idx( CFLibDbKeyHash256 DelSubDep1Id )
	{
		return( readDelSubDep2ByContDelDep1Idx( DelSubDep1Id,
			false ) );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readDelSubDep2ByContDelDep1Idx( CFLibDbKeyHash256 DelSubDep1Id,
		boolean forceRead )
	{
		final String S_ProcName = "readDelSubDep2ByContDelDep1Idx";
		ICFBamDelSubDep2ByContDelDep1IdxKey key = schema.getCFBamBackingStore().getFactoryDelSubDep2().newByContDelDep1IdxKey();
		key.setRequiredDelSubDep1Id( DelSubDep1Id );
		Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> dict;
		if( indexByContDelDep1Idx == null ) {
			indexByContDelDep1Idx = new HashMap< ICFBamDelSubDep2ByContDelDep1IdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep2Obj > >();
		}
		if( ( ! forceRead ) && indexByContDelDep1Idx.containsKey( key ) ) {
			dict = indexByContDelDep1Idx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamDelSubDep2Obj>();
			ICFBamDelSubDep2Obj obj;
			ICFBamDelSubDep2[] recList = schema.getCFBamBackingStore().getTableDelSubDep2().readDerivedByContDelDep1Idx( null,
				DelSubDep1Id );
			ICFBamDelSubDep2 rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamDelSubDep2Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamDelSubDep2Obj realised = (ICFBamDelSubDep2Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByContDelDep1Idx.put( key, dict );
		}
		int len = dict.size();
		ICFBamDelSubDep2Obj arr[] = new ICFBamDelSubDep2Obj[len];
		Iterator<ICFBamDelSubDep2Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamDelSubDep2Obj> arrayList = new ArrayList<ICFBamDelSubDep2Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamDelSubDep2Obj> cmp = new Comparator<ICFBamDelSubDep2Obj>() {
			@Override
			public int compare( ICFBamDelSubDep2Obj lhs, ICFBamDelSubDep2Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamDelSubDep2Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamDelSubDep2Obj readDelSubDep2ByUNameIdx( CFLibDbKeyHash256 DelSubDep1Id,
		String Name )
	{
		return( readDelSubDep2ByUNameIdx( DelSubDep1Id,
			Name,
			false ) );
	}

	@Override
	public ICFBamDelSubDep2Obj readDelSubDep2ByUNameIdx( CFLibDbKeyHash256 DelSubDep1Id,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamDelSubDep2ByUNameIdxKey,
				ICFBamDelSubDep2Obj >();
		}
		ICFBamDelSubDep2ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryDelSubDep2().newByUNameIdxKey();
		key.setRequiredDelSubDep1Id( DelSubDep1Id );
		key.setRequiredName( Name );
		ICFBamDelSubDep2Obj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamDelSubDep2 rec = schema.getCFBamBackingStore().getTableDelSubDep2().readDerivedByUNameIdx( null,
				DelSubDep1Id,
				Name );
			if( rec != null ) {
				obj = (ICFBamDelSubDep2Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamDelSubDep2Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamDelSubDep2Obj readCachedDelSubDep2ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDelSubDep2Obj obj = null;
		obj = readCachedDelSubDep2( Id );
		return( obj );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readCachedDelSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedDelSubDep2ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamDelSubDep2Obj> arrayList = new ArrayList<ICFBamDelSubDep2Obj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamDelSubDep2Obj arr[] = new ICFBamDelSubDep2Obj[len];
				Iterator<ICFBamDelSubDep2Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamDelSubDep2Obj obj;
			Iterator<ICFBamDelSubDep2Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelSubDep2Obj> cmp = new Comparator<ICFBamDelSubDep2Obj>() {
			@Override
			public int compare( ICFBamDelSubDep2Obj lhs, ICFBamDelSubDep2Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readCachedDelSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedDelSubDep2ByDefSchemaIdx";
		ICFBamDelDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamDelSubDep2Obj> arrayList = new ArrayList<ICFBamDelSubDep2Obj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamDelSubDep2Obj arr[] = new ICFBamDelSubDep2Obj[len];
				Iterator<ICFBamDelSubDep2Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamDelSubDep2Obj obj;
			Iterator<ICFBamDelSubDep2Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelSubDep2Obj> cmp = new Comparator<ICFBamDelSubDep2Obj>() {
			@Override
			public int compare( ICFBamDelSubDep2Obj lhs, ICFBamDelSubDep2Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readCachedDelSubDep2ByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedDelSubDep2ByDelDepIdx";
		ICFBamDelDepByDelDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamDelSubDep2Obj> arrayList = new ArrayList<ICFBamDelSubDep2Obj>();
		if( indexByDelDepIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> dict;
			if( indexByDelDepIdx.containsKey( key ) ) {
				dict = indexByDelDepIdx.get( key );
				int len = dict.size();
				ICFBamDelSubDep2Obj arr[] = new ICFBamDelSubDep2Obj[len];
				Iterator<ICFBamDelSubDep2Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamDelSubDep2Obj obj;
			Iterator<ICFBamDelSubDep2Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelSubDep2Obj> cmp = new Comparator<ICFBamDelSubDep2Obj>() {
			@Override
			public int compare( ICFBamDelSubDep2Obj lhs, ICFBamDelSubDep2Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> readCachedDelSubDep2ByContDelDep1Idx( CFLibDbKeyHash256 DelSubDep1Id )
	{
		final String S_ProcName = "readCachedDelSubDep2ByContDelDep1Idx";
		ICFBamDelSubDep2ByContDelDep1IdxKey key = schema.getCFBamBackingStore().getFactoryDelSubDep2().newByContDelDep1IdxKey();
		key.setRequiredDelSubDep1Id( DelSubDep1Id );
		ArrayList<ICFBamDelSubDep2Obj> arrayList = new ArrayList<ICFBamDelSubDep2Obj>();
		if( indexByContDelDep1Idx != null ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> dict;
			if( indexByContDelDep1Idx.containsKey( key ) ) {
				dict = indexByContDelDep1Idx.get( key );
				int len = dict.size();
				ICFBamDelSubDep2Obj arr[] = new ICFBamDelSubDep2Obj[len];
				Iterator<ICFBamDelSubDep2Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamDelSubDep2Obj obj;
			Iterator<ICFBamDelSubDep2Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamDelSubDep2Obj> cmp = new Comparator<ICFBamDelSubDep2Obj>() {
			@Override
			public int compare( ICFBamDelSubDep2Obj lhs, ICFBamDelSubDep2Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamDelSubDep2Obj readCachedDelSubDep2ByUNameIdx( CFLibDbKeyHash256 DelSubDep1Id,
		String Name )
	{
		ICFBamDelSubDep2Obj obj = null;
		ICFBamDelSubDep2ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryDelSubDep2().newByUNameIdxKey();
		key.setRequiredDelSubDep1Id( DelSubDep1Id );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamDelSubDep2Obj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamDelSubDep2Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public void deepDisposeDelSubDep2ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDelSubDep2Obj obj = readCachedDelSubDep2ByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeDelSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeDelSubDep2ByTenantIdx";
		ICFBamDelSubDep2Obj obj;
		List<ICFBamDelSubDep2Obj> arrayList = readCachedDelSubDep2ByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamDelSubDep2Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeDelSubDep2ByDefSchemaIdx";
		ICFBamDelSubDep2Obj obj;
		List<ICFBamDelSubDep2Obj> arrayList = readCachedDelSubDep2ByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamDelSubDep2Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelSubDep2ByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposeDelSubDep2ByDelDepIdx";
		ICFBamDelSubDep2Obj obj;
		List<ICFBamDelSubDep2Obj> arrayList = readCachedDelSubDep2ByDelDepIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamDelSubDep2Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelSubDep2ByContDelDep1Idx( CFLibDbKeyHash256 DelSubDep1Id )
	{
		final String S_ProcName = "deepDisposeDelSubDep2ByContDelDep1Idx";
		ICFBamDelSubDep2Obj obj;
		List<ICFBamDelSubDep2Obj> arrayList = readCachedDelSubDep2ByContDelDep1Idx( DelSubDep1Id );
		if( arrayList != null )  {
			Iterator<ICFBamDelSubDep2Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeDelSubDep2ByUNameIdx( CFLibDbKeyHash256 DelSubDep1Id,
		String Name )
	{
		ICFBamDelSubDep2Obj obj = readCachedDelSubDep2ByUNameIdx( DelSubDep1Id,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamDelSubDep2Obj updateDelSubDep2( ICFBamDelSubDep2Obj Obj ) {
		ICFBamDelSubDep2Obj obj = Obj;
		schema.getCFBamBackingStore().getTableDelSubDep2().updateDelSubDep2( null,
			Obj.getDelSubDep2Rec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getDelSubDep2TableObj().getClassCode() ) {
			obj = (ICFBamDelSubDep2Obj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteDelSubDep2( ICFBamDelSubDep2Obj Obj ) {
		ICFBamDelSubDep2Obj obj = Obj;
		schema.getCFBamBackingStore().getTableDelSubDep2().deleteDelSubDep2( null,
			obj.getDelSubDep2Rec() );
		Obj.forget();
	}

	@Override
	public void deleteDelSubDep2ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamDelSubDep2Obj obj = readDelSubDep2(Id);
		if( obj != null ) {
			ICFBamDelSubDep2EditObj editObj = (ICFBamDelSubDep2EditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamDelSubDep2EditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeDelSubDep2ByIdIdx( Id );
	}

	@Override
	public void deleteDelSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep2Obj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableDelSubDep2().deleteDelSubDep2ByTenantIdx( null,
				TenantId );
			Iterator<ICFBamDelSubDep2Obj> iter = dict.values().iterator();
			ICFBamDelSubDep2Obj obj;
			List<ICFBamDelSubDep2Obj> toForget = new LinkedList<ICFBamDelSubDep2Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelSubDep2().deleteDelSubDep2ByTenantIdx( null,
				TenantId );
		}
		deepDisposeDelSubDep2ByTenantIdx( TenantId );
	}

	@Override
	public void deleteDelSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamDelDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamDelDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep2Obj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableDelSubDep2().deleteDelSubDep2ByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamDelSubDep2Obj> iter = dict.values().iterator();
			ICFBamDelSubDep2Obj obj;
			List<ICFBamDelSubDep2Obj> toForget = new LinkedList<ICFBamDelSubDep2Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelSubDep2().deleteDelSubDep2ByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeDelSubDep2ByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteDelSubDep2ByDelDepIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamDelDepByDelDepIdxKey key = schema.getCFBamBackingStore().getFactoryDelDep().newByDelDepIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByDelDepIdx == null ) {
			indexByDelDepIdx = new HashMap< ICFBamDelDepByDelDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep2Obj > >();
		}
		if( indexByDelDepIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> dict = indexByDelDepIdx.get( key );
			schema.getCFBamBackingStore().getTableDelSubDep2().deleteDelSubDep2ByDelDepIdx( null,
				RelationId );
			Iterator<ICFBamDelSubDep2Obj> iter = dict.values().iterator();
			ICFBamDelSubDep2Obj obj;
			List<ICFBamDelSubDep2Obj> toForget = new LinkedList<ICFBamDelSubDep2Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDelDepIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelSubDep2().deleteDelSubDep2ByDelDepIdx( null,
				RelationId );
		}
		deepDisposeDelSubDep2ByDelDepIdx( RelationId );
	}

	@Override
	public void deleteDelSubDep2ByContDelDep1Idx( CFLibDbKeyHash256 DelSubDep1Id )
	{
		ICFBamDelSubDep2ByContDelDep1IdxKey key = schema.getCFBamBackingStore().getFactoryDelSubDep2().newByContDelDep1IdxKey();
		key.setRequiredDelSubDep1Id( DelSubDep1Id );
		if( indexByContDelDep1Idx == null ) {
			indexByContDelDep1Idx = new HashMap< ICFBamDelSubDep2ByContDelDep1IdxKey,
				Map< CFLibDbKeyHash256, ICFBamDelSubDep2Obj > >();
		}
		if( indexByContDelDep1Idx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamDelSubDep2Obj> dict = indexByContDelDep1Idx.get( key );
			schema.getCFBamBackingStore().getTableDelSubDep2().deleteDelSubDep2ByContDelDep1Idx( null,
				DelSubDep1Id );
			Iterator<ICFBamDelSubDep2Obj> iter = dict.values().iterator();
			ICFBamDelSubDep2Obj obj;
			List<ICFBamDelSubDep2Obj> toForget = new LinkedList<ICFBamDelSubDep2Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByContDelDep1Idx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableDelSubDep2().deleteDelSubDep2ByContDelDep1Idx( null,
				DelSubDep1Id );
		}
		deepDisposeDelSubDep2ByContDelDep1Idx( DelSubDep1Id );
	}

	@Override
	public void deleteDelSubDep2ByUNameIdx( CFLibDbKeyHash256 DelSubDep1Id,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamDelSubDep2ByUNameIdxKey,
				ICFBamDelSubDep2Obj >();
		}
		ICFBamDelSubDep2ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryDelSubDep2().newByUNameIdxKey();
		key.setRequiredDelSubDep1Id( DelSubDep1Id );
		key.setRequiredName( Name );
		ICFBamDelSubDep2Obj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableDelSubDep2().deleteDelSubDep2ByUNameIdx( null,
				DelSubDep1Id,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableDelSubDep2().deleteDelSubDep2ByUNameIdx( null,
				DelSubDep1Id,
				Name );
		}
		deepDisposeDelSubDep2ByUNameIdx( DelSubDep1Id,
				Name );
	}
}